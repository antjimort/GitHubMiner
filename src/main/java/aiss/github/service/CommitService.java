package aiss.github.service;

import aiss.github.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Commit> findAllCommitsFromRepo(String owner, String repo) {

        String url = baseUrl + "/repos/" + owner + "/" + repo + "/commits";

        List<Commit> commits = null;

        try {

            Commit[] commitArray = restTemplate.getForObject(url, Commit[].class);
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
