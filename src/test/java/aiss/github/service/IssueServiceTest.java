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

 /*   @Test
    @DisplayName("Get all issues from the authenticated user")
    void findIssuesFromAuthenticatedUser() {
        List<Issue> issues = service.findIssuesFromAuthenticatedUser();
        assertTrue(!issues.isEmpty(), "The list of issues from the authenticated user is empty");
        System.out.println(issues);
    }
*/
 /*   @Test
    @DisplayName("Get single issue")
    void findSingleIssue() {
        String owner = "DuGuQiuBai";
        String repo = "Java";
        String issueId = "43";
        Issue issue = service.findSingleIssue(owner, repo, issueId);
        assertTrue(!issue.equals(null), "This issue is null");
        System.out.println(issue);
    } */

    @Test
    @DisplayName("Get all issues from an existing repository")
    void findAllIssuesFromRepo() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        List<Issue> issues = service.findAllIssuesFromRepo(owner, repo);
        System.out.println(issues);
    }

    /*@Test
    @DisplayName("Get issues by state from an existing repository")
    void findProjectIssuesByState() {
        String owner = "DuGuQiuBai";
        String repo = "Java";
        String state = "open";
        List<Issue> issues = service.findProjectIssuesByState(owner, repo, state);
        assertTrue(!issues.isEmpty(), "The list of issues is empty");
        System.out.println(issues);
    } */
}