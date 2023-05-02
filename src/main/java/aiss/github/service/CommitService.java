package aiss.github.service;

import aiss.github.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service

public class CommitService {

    @Autowired
    RestTemplate restTemplate; // Para hacer las llamadas a la api

    String token = "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";
    String baseUrl = "https://api.github.com";

    public List<Commit> findAllCommitsFromRepo(String owner, String repo) {

        String url = baseUrl + "/repos/" + owner + "/" + repo + "/commits";
        //https://api.github.com/repos/DuGuQiuBai/Java/commits

        List<Commit> commits = null;

        try {

            Commit[] commitArray = restTemplate.getForObject(url, Commit[].class);
            commits = Arrays.stream(commitArray).toList();

        } catch (RestClientException ex) {

            System.out.println("Error while retrieving commits from repository" + repo + ":"
                    + ex.getLocalizedMessage());//sout
        }


        return commits;

    }

}
