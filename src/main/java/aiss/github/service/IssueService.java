package aiss.github.service;

import aiss.github.model.Commit;
import aiss.github.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Issue> findAllIssuesFromRepo(String owner, String repo, Integer sinceIssues, Integer maxPages){

        LocalDate date = LocalDate.now().minusDays(sinceIssues);
        String formattedDate = date.format(DateTimeFormatter.ISO_DATE);

        int currentPage = 1;
        int totalPages = maxPages;

        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Issue> entity = new HttpEntity<>(headers);

        List<Issue> issues = new ArrayList<>();

        try {
            while (currentPage <= totalPages) {
                String url = baseUrl + "/repos/" + owner + "/" + repo + "/issues?since=" + formattedDate + "&page=" + currentPage;
                ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Issue[].class);
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

        List<Issue> parsedIssues = parseIssues(issues);

        return parsedIssues;
    }

    private static List<Issue> parseIssues(List<Issue> issues) {
        List<Issue> parsedIssues = new ArrayList<>();
        for(Issue issue: issues){
            Issue parsedIssue = Issue.of(issue);
            parsedIssues.add(parsedIssue);
        }
        return parsedIssues;
    }

    private void setCommentsIntoIssues(String owner, String repo, List<Issue> issues, ResponseEntity<Issue[]> response) {
        for(Issue issue: response.getBody()){
            issue.setCommentsList(commentService.findAllCommentsFromIssue(owner, repo, issue.getNumber().toString()));
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
