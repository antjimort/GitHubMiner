package aiss.github.service.impl;

import aiss.github.model.Project;
import aiss.github.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class GitMinerServiceImpl implements GitMinerService {
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void sendProject(Project project) {
        HttpEntity<Project> entity = new HttpEntity<>(project);
        restTemplate.exchange(GIT_MINER_URI, HttpMethod.POST, entity, Project.class);
    }
}
