package aiss.github.service;

import aiss.github.model.Comment;
import aiss.github.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class CommentService {


    @Autowired
    RestTemplate restTemplate;
    String baseUrl = "https://api.github.com";

    String token = "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";

    HttpHeaders headers = new HttpHeaders();

    public List<Comment> findAllCommentsFromIssue(String owner, String repo, String issueId){
        String url = baseUrl + "/repos/" + owner + "/" + repo + "/issues/" + issueId + "/comments";
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Comment> entity = new HttpEntity<>(headers);
        List<Comment> comments = new ArrayList<>();

        try {
            ResponseEntity<Comment[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Comment[].class);
            comments = Arrays.stream(response.getBody()).toList();

        } catch (RestClientException ex) {
            System.out.println("Error while retrieving comments from the issue: "  + issueId + ":"
                    + ex.getLocalizedMessage());
        }

        List<Comment> parsedComments = new ArrayList<>();
        for (Comment comment: comments){
            Comment parsedComment = Comment.of(comment);
            parsedComments.add(parsedComment);
        }

        return parsedComments;
    }

}
