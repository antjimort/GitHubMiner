package aiss.github.service;

import aiss.github.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    String token = "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";
    String baseUrl = "https://api.github.com";
    HttpHeaders headers = new HttpHeaders();

    public List<Issue> findAllIssuesFromRepo(String owner, String repo){

        String url = baseUrl +"/repos/"+ owner +"/"+ repo + "/issues";
        List<Issue> issues = new ArrayList<>();
        try{
            Issue[] issueArray = restTemplate.getForObject(url, Issue[].class);
            issues = Arrays.stream(issueArray).toList();
        }catch (RestClientException ex){
            System.out.println("Error while getting issues from project "+repo+" of owner "+owner+" : "+ex.getLocalizedMessage());
        }
        return issues;
    }

    public Issue findSingleIssue(String owner, String repo, String IssueId){

        String url = baseUrl +"/repos/"+ owner +"/"+ repo + "/issues/"+ IssueId;
        Issue issue = null;
        try{
            issue = restTemplate.getForObject(url,Issue.class);
        }
        catch (RestClientException ex){
            System.out.println("Error while getting the issue from repo "+repo+" of owner "+
                    owner+" with id: "+IssueId+" : "+ex.getLocalizedMessage());
        }
        return issue;
    }

    public List<Issue> findProjectIssuesByState(String owner, String repo, String state){

        if(!(state.equals("open") || state.equals("closed") || state.equals("all"))){
            throw new IllegalArgumentException("State param must be open, closed or all");
        }

        String url = baseUrl + "/repos/" +owner+"/"+repo+"/issues?state="+ state;
        List<Issue> issues = new ArrayList<>();
        try{
            Issue [] issuesArray = restTemplate.getForObject(url, Issue[].class);
            issues = Arrays.stream(issuesArray).toList();
        }catch(RestClientException ex){
            System.out.println("Error while getting issues from repo "
                    +repo+" of owner "+owner+" with state: "+state+" : "+ex.getLocalizedMessage());
        }
        return issues;
    }

    public List<Issue> findIssuesFromAuthenticatedUser(){
        String url = baseUrl + "/issues";
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Issue> entity = new HttpEntity<>(headers);
        List<Issue> issues = new ArrayList<>();
        try{
            ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Issue[].class);
            issues = Arrays.stream(response.getBody()).toList();
        }catch(RestClientException ex){
            System.out.println("Error while retrieving issues from the authenticated user: "+ex.getLocalizedMessage());
        }
        return issues;


    }
}
