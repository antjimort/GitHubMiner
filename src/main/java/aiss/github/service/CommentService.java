package aiss.github.service;

import aiss.github.model.Comment;

import java.util.List;

public interface CommentService {
    String COMMENTS = "/comments";

    List<Comment> findAllCommentsFromIssue(String owner, String repo, String issueId);
}
