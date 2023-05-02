package aiss.github.service;

import aiss.github.Commit;
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
    @DisplayName("Find all commit from an existing repo")
    void findAllCommitsFromRepo() {

        String owner = "DuGuQiuBai";
        String repo = "Java";

        List<Commit> commits = service.findAllCommitsFromRepo(owner, repo);
        assertTrue(!commits.isEmpty(), "The list is empty");
        System.out.println(commits);

    }
}