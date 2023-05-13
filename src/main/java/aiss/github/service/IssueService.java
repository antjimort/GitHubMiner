package aiss.github.service;

import aiss.github.model.IssueData;
import aiss.github.model.IssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service

public class IssueService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentService commentService;
    String baseUrl = "https://api.github.com";

    String token = "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";

    HttpHeaders headers = new HttpHeaders();

    public List<IssueResponse> findAllIssuesFromRepo(String owner, String repo, Integer sinceIssues, Integer maxPages){

        LocalDate date = LocalDate.now().minusDays(sinceIssues);
        String formattedDate = date.format(DateTimeFormatter.ISO_DATE);

        int currentPage = 1;
        int totalPages = maxPages;

        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<IssueData> entity = new HttpEntity<>(headers);

        List<IssueData> issues = new ArrayList<>();

        try {
            while (currentPage <= totalPages) {
                String url = baseUrl + "/repos/" + owner + "/" + repo + "/issues?since=" + formattedDate + "&page=" + currentPage;
                ResponseEntity<IssueData[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, IssueData[].class);
                HttpHeaders responseHeaders = response.getHeaders();
                String nextPageUrl = getNextPageUrl(responseHeaders);
                setCommentsIntoIssues(owner, repo, issues, response);

                if (nextPageUrl == null) {
                    break;
                }
                currentPage++;
            }
        } catch (RestClientException ex) {
            System.out.println("Error while getting issues from project " + repo + " of owner " + owner + " : " + ex.getLocalizedMessage());
        }

        List<IssueResponse> parsedIssues = parseIssues(issues);

        return parsedIssues;
    }

    private static List<IssueResponse> parseIssues(List<IssueData> issues) {
        List<IssueResponse> parsedIssues = new ArrayList<>();
        for(IssueData issue: issues){
            IssueResponse parsedIssue = IssueResponse.of(issue);
            parsedIssue.setComments(issue.getCommentList());
            parsedIssues.add(parsedIssue);
        }
        return parsedIssues;
    }

    private void setCommentsIntoIssues(String owner, String repo, List<IssueData> issues, ResponseEntity<IssueData[]> response) {
        for(IssueData issue: response.getBody()){
            issue.setCommentList(commentService.findAllCommentsFromIssue(owner, repo, issue.getNumber().toString()));
            issues.add(issue);
        }
    }

    // Source: https://github.com/hub4j/github-api/blob/bc132521ddec315f845131358e559930451db1ac/src/main/java/org/kohsuke/github/GitHubPageIterator.java
    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }

}