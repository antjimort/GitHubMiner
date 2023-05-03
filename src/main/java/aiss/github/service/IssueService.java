package aiss.github.service;

import aiss.github.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Issue> findAllIssuesFromRepo(String owner, String repo){

        //https://api.github.com/repos/DuGuQiuBai/Java/issues
        String uri = baseUrl +"/repos/"+ owner +"/"+ repo + "/issues";
        List<Issue> issues = new ArrayList<>();
        try{
            Issue[] issueArray = restTemplate.getForObject(uri, Issue[].class);
            issues = Arrays.stream(issueArray).toList();
        }catch (RestClientException ex){
            System.out.println("Error while getting issues from project "+repo+" of owner "+owner+" : "+ex.getLocalizedMessage());
        }
        return issues;
    }

    public Issue findIssueFromRepo(String owner, String repo, String IssueId){

        //https://api.github.com/repos/DuGuQiuBai/Java/issues/43
        String uri = baseUrl +"/repos/"+ owner +"/"+ repo + "/issues"+ IssueId;
        Issue issue = null;
        try{
            issue = restTemplate.getForObject(uri,Issue.class);
        }
        catch (RestClientException ex){
            System.out.println("Error while getting the issue from repo "+repo+" of owner "+
                    owner+" with id: "+IssueId+" : "+ex.getLocalizedMessage());
        }
        return issue;
    }

    public List<Issue> findIssueOpenFromProject(String owner, String repo, String state){

        //https://api.github.com/repos/DuGuQiuBai/Java/issues?state=open

        String uri = baseUrl + "/repos/" +owner+repo+"issues?state="+ state;
        List<Issue> issues = new ArrayList<>();
        try{
            Issue [] issuesArray = restTemplate.getForObject(uri, Issue[].class);
            issues = Arrays.stream(issuesArray).toList();
        }catch(RestClientException ex){
            System.out.println("Error while getting issues from repo "
                    +repo+" of owner "+owner+" with state: "+state+" : "+ex.getLocalizedMessage());
        }
        return issues;
    }

    public List<Issue> findIssuesFromUser(){

        //https://api.github.com/issues
        //TO DO : Añadir el token

        String uri = baseUrl + "issues";
        List<Issue> issues = new ArrayList<>();
        try{
            Issue [] issuesArray = restTemplate.getForObject(uri, Issue[].class);
            issues = Arrays.stream(issuesArray).toList();
        }catch(RestClientException ex){
            System.out.println("Error" + ": "+ex.getLocalizedMessage());
        }
        return issues;


    }
}
