package aiss.github.service;

import aiss.github.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;

    String token = "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";
    HttpHeaders headers = new HttpHeaders();

    public Project getProject(String owner, String repoName, Integer sinceCommits,
                              Integer sinceIssues, Integer maxPages){
        String url = "https://api.github.com/repos/"+owner+"/"+repoName;
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Project> entity = new HttpEntity<>(headers);
        Project project = null;
        try{
            ResponseEntity<Project> response = restTemplate.exchange(url, HttpMethod.GET,entity, Project.class);
            project = response.getBody();
        } catch (RestClientException ex){
            System.out.println("Error while retrieving project of owner "+owner+ " and repo "+repoName+": "+ex.getLocalizedMessage());
        }
        Project parsedProject = Project.of(project);
        parsedProject.setCommits(commitService.findAllCommitsFromRepo(owner, repoName, sinceCommits, maxPages));
        parsedProject.setIssues(issueService.findAllIssuesFromRepo(owner, repoName, sinceIssues, maxPages));
        return parsedProject;
    }

}
