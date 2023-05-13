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

        List<Commit> commits = service.findAllCommitsFromRepo(owner, repo, 10, 4);
        System.out.println(commits.size());
        System.out.println(commits);

    }
}