package aiss.github.service;

import aiss.github.model.Project;

public interface ProjectService {
    Project getProject(String owner, String repoName, Integer sinceCommits,
                       Integer sinceIssues, Integer maxPages);
}
