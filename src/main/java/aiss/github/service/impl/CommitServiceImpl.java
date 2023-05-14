package aiss.github.service.impl;

import aiss.github.model.Commit;
import aiss.github.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aiss.github.utils.ProjectUtils.*;

@Service

public class CommitServiceImpl implements CommitService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Commit> findAllCommitsFromRepo(String owner, String repo, Integer sinceCommits,
                                                Integer maxPages) {

        LocalDate date = LocalDate.now().minusDays(sinceCommits);
        String formattedDate = date.format(DateTimeFormatter.ISO_DATE);

        int currentPage = 1;
        int totalPages = maxPages;

        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, BEARER + TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Commit> entity = new HttpEntity<>(headers);

        List<Commit> commits = new ArrayList<>();

        try{
            while(currentPage <= totalPages) {
                String url = GITHUB_URL + owner + "/" + repo + COMMITS_SINCE + formattedDate + PAGE + currentPage;
                ResponseEntity<Commit[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Commit[].class);
                HttpHeaders responseHeaders = response.getHeaders();
                String nextPageUrl = getNextPageUrl(responseHeaders);
                if(response.getBody() != null){
                    commits.addAll(Arrays.stream(response.getBody()).toList());
                    if (nextPageUrl == null) {
                        break;
                    }
                } else{
                    System.out.println("Error while retrieving commits from repository" + repo);
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
