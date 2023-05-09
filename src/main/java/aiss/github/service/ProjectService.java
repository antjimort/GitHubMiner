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

    public List<Project> findAllProjectsFromOrg(String org){
        String url = baseUrl + "/orgs/"+org+"/projects";
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Project> entity = new HttpEntity<>(headers);
        List<Project> projects = new ArrayList<>();

        try{
            ResponseEntity<Project[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Project[].class);
            projects = Arrays.stream(response.getBody()).toList();
        } catch (RestClientException ex){
            System.out.println("Error while retrieving "+org+" projects: "+ex.getLocalizedMessage());
        }

        return projects;
    }

    public Project findProject(String projectId){
        String url = baseUrl + "/projects/"+projectId;
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Project> entity = new HttpEntity<>(headers);
        Project project = null;

        try{
            project = restTemplate.exchange(url, HttpMethod.GET, entity, Project.class).getBody();
        } catch (RestClientException ex){
            System.out.println("Error while retrieving project with id "+projectId+":"+ex.getLocalizedMessage());
        }
        Project parsedProject = Project.of(project);
        //parsedProject.setCommits(commitService.findAllCommitsFromRepo());
        return project;
    }

}
