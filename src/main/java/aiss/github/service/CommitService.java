package aiss.github.service;

import aiss.github.model.Commit;
import aiss.github.model.Project;
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

public class CommitService {

    @Autowired
    RestTemplate restTemplate;
    String baseUrl = "https://api.github.com";
    String token = "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";

    HttpHeaders headers = new HttpHeaders();


    public List<Commit> findAllCommitsFromRepo(String owner, String repo, Integer sinceCommits,
                                                Integer maxPages) {

        LocalDate date = LocalDate.now().minusDays(sinceCommits);
        String formattedDate = date.format(DateTimeFormatter.ISO_DATE);

        int currentPage = 1;
        int totalPages = maxPages;

        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Commit> entity = new HttpEntity<>(headers);

        List<Commit> commits = new ArrayList<>();

        try{
            while(currentPage <= totalPages) {
                String url = baseUrl + "/repos/" + owner + "/" + repo + "/commits?since=" + formattedDate + "&page=" + currentPage;
                ResponseEntity<Commit[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Commit[].class);
                HttpHeaders responseHeaders = response.getHeaders();
                String nextPageUrl = getNextPageUrl(responseHeaders);
                commits.addAll(Arrays.stream(response.getBody()).toList());
                if (nextPageUrl == null) {
                    break;
                }
                currentPage++;
            }
        } catch (RestClientException ex){
            System.out.println("Error while retrieving commits from repository" + repo + ":"
                    + ex.getLocalizedMessage());
        }


        List<Commit> parsedCommits = parseCommits(commits);

        return parsedCommits;

    }

    private static List<Commit> parseCommits(List<Commit> commits) {
        List<Commit> parsedCommits = new ArrayList<>();
        for(Commit c: commits){
            Commit parsedCommit = Commit.of(c);
            parsedCommits.add(parsedCommit);
        }
        return parsedCommits;
    }

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
