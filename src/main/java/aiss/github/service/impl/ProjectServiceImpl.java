package aiss.github.service.impl;

import aiss.github.model.Project;
import aiss.github.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import static aiss.github.utils.ProjectUtils.*;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommitServiceImpl commitService;
    @Autowired
    IssueServiceImpl issueService;

    @Override
    public Project getProject(String owner, String repoName, Integer sinceCommits,
                              Integer sinceIssues, Integer maxPages){
        String url = GITHUB_URL + owner + "/" + repoName;
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, BEARER + TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Project> entity = new HttpEntity<>(headers);
        Project project = null;
        try{
            ResponseEntity<Project> response = restTemplate.exchange(url, HttpMethod.GET,entity, Project.class);
            project = response.getBody();
        } catch (RestClientException ex){
            System.out.println("Error while retrieving project of owner "+owner+ " and repo "+repoName+": "+ex.getLocalizedMessage());
        }
        if(project!=null){
            Project parsedProject = Project.of(project);
            parsedProject.setCommits(commitService.findAllCommitsFromRepo(owner, repoName, sinceCommits, maxPages));
            parsedProject.setIssues(issueService.findAllIssuesFromRepo(owner, repoName, sinceIssues, maxPages));
            return parsedProject;
        } else {
            System.out.println("Error while retrieving project of owner "+owner+ " and repo "+repoName);
            throw new RuntimeException();
        }
    }

}
