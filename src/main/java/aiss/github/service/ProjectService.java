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
        return project;
    }

}
