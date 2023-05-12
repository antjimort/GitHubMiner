package aiss.github.service;

import aiss.github.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService service;

    @Test
    @DisplayName("Get all issues from an existing repository")
    void findAllIssuesFromRepo() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        List<Issue> issues = service.findAllIssuesFromRepo(owner, repo, 20, 2);
        System.out.println(issues.size());
        System.out.println(issues);
    }
}