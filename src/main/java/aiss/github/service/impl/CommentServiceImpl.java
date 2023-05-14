package aiss.github.service.impl;

import aiss.github.model.Comment;
import aiss.github.service.CommentService;
import aiss.github.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aiss.github.utils.ProjectUtils.*;

@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Comment> findAllCommentsFromIssue(String owner, String repo, String issueId){

        String url = GITHUB_URL + owner + "/" + repo + ISSUES + issueId + COMMENTS;
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, BEARER + ProjectUtils.TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Comment> entity = new HttpEntity<>(headers);
        List<Comment> comments = new ArrayList<>();

        try {
            ResponseEntity<Comment[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Comment[].class);
            if(response.getBody()!=null){
                comments = Arrays.stream(response.getBody()).toList();
            } else {
                System.out.println("Error while retrieving comments from the issue: "  + issueId);
            }
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
