package aiss.github.service;

import aiss.github.model.Commit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {

    @Autowired
    CommitService service;

    @Test
    @DisplayName("Find all commits from an existing repo")
    void findAllCommitsFromRepo() {

        String owner = "spring-projects";
        String repo = "spring-framework";

        List<Commit> commits = service.findAllCommitsFromRepo(owner, repo);
        assertTrue(!commits.isEmpty(), "The list is empty");
        System.out.println(commits);

    }

  /*  @Test
    @DisplayName("Find commit from an existing repo")
    void findCommitFromRepo() {

        String owner = "spring-projects";
        String repo = "spring-framework";
        String commitId = "963274";

        Commit commit = service.findCommitFromRepo(owner, repo, commitId);
        assertTrue(!commit.equals(null), "The commit is null");
        System.out.println(commit);
    } */
}