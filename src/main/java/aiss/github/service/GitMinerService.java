package aiss.github.service;

import aiss.github.model.Project;
import org.springframework.stereotype.Service;


public interface GitMinerService {
    String GIT_MINER_URI = "http://localhost:8080/gitminer/projects";

    void sendProject(Project project);
}
