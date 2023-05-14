package aiss.github.service;

import aiss.github.model.IssueResponse;
import aiss.github.service.impl.IssueServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueServiceImpl service;

    @Test
    @DisplayName("Get all issues from an existing repository")
    void findAllIssuesFromRepo() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        List<IssueResponse> issues = service.findAllIssuesFromRepo(owner, repo, 10, 1);
        System.out.println(issues.size());
        System.out.println(issues);
    }
}