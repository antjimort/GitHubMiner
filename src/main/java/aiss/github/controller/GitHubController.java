package aiss.github.controller;

import aiss.github.model.Project;
import aiss.github.service.ProjectService;
import aiss.github.service.impl.GitMinerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class GitHubController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private GitMinerServiceImpl gitMinerServiceImpl;

    @GetMapping("/{owner}/{repoName}")
    public Project fetchProject(@PathVariable String owner, @PathVariable String repoName,
                                @RequestParam(defaultValue = "5") Integer sinceCommits,
                                @RequestParam(defaultValue = "20") Integer sinceIssues,
                                @RequestParam(defaultValue = "2") Integer maxPages ){
        Project project = projectService.getProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
        gitMinerServiceImpl.sendProject(project);
        return project;
    }



}
