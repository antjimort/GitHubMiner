package aiss.github.service;

import aiss.github.model.Commit;
import aiss.github.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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


    public List<Commit> findAllCommitsFromRepo(String owner, String repo) {

        String url = baseUrl + "/repos/" + owner + "/" + repo + "/commits";

        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Commit> entity = new HttpEntity<>(headers);

        List<Commit> commits = null;

        try {
            ResponseEntity<Commit[]> response = restTemplate.exchange(url, HttpMethod.GET,entity, Commit[].class);
            Commit[] commitArray = response.getBody();
            commits = Arrays.stream(commitArray).toList();

        } catch (RestClientException ex) {

            System.out.println("Error while retrieving commits from repository" + repo + ":"
                    + ex.getLocalizedMessage());
        }

        List<Commit> parsedCommits = new ArrayList<>();
        for(Commit c: commits){
            Commit parsedCommit = Commit.of(c);
            parsedCommits.add(parsedCommit);
        }

        return parsedCommits;

    }

   /* public Commit findCommitFromRepo(String owner, String repo, String commitId) {

        String url = baseUrl + "/repos/" + owner + "/" + repo + "/commits/" + commitId;

        Commit commit = null;

        try {
            commit = restTemplate.getForObject(url, Commit.class);

        } catch (RestClientException ex) {

            System.out.println("Error while retrieving commit with id: " + commitId + ":"
                    + ex.getLocalizedMessage());
        }

        Commit parsedCommit = Commit.of(commit);

        return parsedCommit;
    } */


}
