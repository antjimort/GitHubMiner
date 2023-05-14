package aiss.github.service;

import aiss.github.model.Comment;
import aiss.github.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentServiceImpl service;

    @DisplayName("Find comments from issue")
    @Test
    void findAllCommentsFromIssue() {
        //https://api.github.com/repos/DynxstyGIT/DIH4JDA/issues/48/comments

        String owner = "spring-projects";
        String repo = "spring-framework";
        String issueId = "30404" ;

        List<Comment> comments = service.findAllCommentsFromIssue(owner, repo, issueId);
        //assertTrue(!comments.isEmpty(), "The list is empty");
        System.out.println(comments);

    }
}