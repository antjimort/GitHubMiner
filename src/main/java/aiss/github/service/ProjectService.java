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
    String baseUrl = "https://api.github.com";
    HttpHeaders headers = new HttpHeaders();

    public Project getProject(String owner, String repoName){
        String url = "https://api.github.com/repos/"+owner+"/"+repoName;
        Project project = null;
        try{
            project = restTemplate.getForObject(url, Project.class);
        } catch (RestClientException ex){
            System.out.println("Error while retrieving project of owner "+owner+ " and repo "+repoName+": "+ex.getLocalizedMessage());
        }
        Project parsedProject = Project.of(project);
        parsedProject.setCommits(commitService.findAllCommitsFromRepo(owner, repoName));
        parsedProject.setIssues(issueService.findAllIssuesFromRepo(owner, repoName));
        return parsedProject;
    }

}
