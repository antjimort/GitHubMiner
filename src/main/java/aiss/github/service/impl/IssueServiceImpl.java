package aiss.github.service.impl;

import aiss.github.model.IssueData;
import aiss.github.model.IssueResponse;
import aiss.github.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static aiss.github.utils.ProjectUtils.*;

@Service

public class IssueServiceImpl implements IssueService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentServiceImpl commentService;

    @Override
    public List<IssueResponse> findAllIssuesFromRepo(String owner, String repo, Integer sinceIssues, Integer maxPages){

        LocalDate date = LocalDate.now().minusDays(sinceIssues);
        String formattedDate = date.format(DateTimeFormatter.ISO_DATE);

        int currentPage = 1;
        int totalPages = maxPages;

        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, BEARER + TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<IssueData> entity = new HttpEntity<>(headers);

        List<IssueData> issues = new ArrayList<>();

        try {
            while (currentPage <= totalPages) {
                String url = GITHUB_URL + owner + "/" + repo + ISSUES_SINCE + formattedDate + PAGE + currentPage;
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

        return parseIssues(issues);
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
       if(response.getBody()!=null){
           for(IssueData issue: response.getBody()){
               issue.setCommentList(commentService.findAllCommentsFromIssue(owner, repo, issue.getNumber().toString()));
               issues.add(issue);
           }
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