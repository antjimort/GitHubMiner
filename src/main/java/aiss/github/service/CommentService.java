package aiss.github.service;

import aiss.github.model.Comment;
import aiss.github.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service

public class CommentService {


    @Autowired
    RestTemplate restTemplate; // Para hacer las llamadas a la api

    String token = "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";
    String baseUrl = "https://api.github.com";

    public List<Comment> findAllCommentsFromRepo(String owner, String repo){

        //https://api.github.com/repos/DynxstyGIT/DIH4JDA/issues/comments

        String url = baseUrl + "/repos/" + owner + "/" + repo + "/issues/" + "comments";

         List<Comment> comments = null;

        try {

            Comment[] commentArray = restTemplate.getForObject(url, Comment[].class);
            comments = Arrays.stream(commentArray).toList();

        } catch (RestClientException ex) {

            System.out.println("Error while retrieving comments from the issues of repo: "  + repo + ":"
                    + ex.getLocalizedMessage());
        }

        return comments;

    }

    public Comment findCommentFromIssue(String owner, String repo, String commentId){


        //https://api.github.com/repos/DynxstyGIT/DIH4JDA/issues/comments/1124218931


        String url = baseUrl + "/repos/" + owner + "/" + repo + "/issues/" + "comments/" + commentId;

        Comment comment = null;

        try {
            comment = restTemplate.getForObject(url, Comment.class);

        } catch (RestClientException ex) {

            System.out.println("Error while retrieving comment with id: " + commentId + ":"
                    + ex.getLocalizedMessage());
        }

        return comment;
    }

    public List<Comment> findAllCommentsFromIssue(String owner, String repo, String issueId){

        //https://api.github.com/repos/DynxstyGIT/DIH4JDA/issues/48/comments

        String url = baseUrl + "/repos/" + owner + "/" + repo + "/issues/" + issueId + "/comments";

        List<Comment> comments = null;

        try {

            Comment[] commentArray = restTemplate.getForObject(url, Comment[].class);
            comments = Arrays.stream(commentArray).toList();

        } catch (RestClientException ex) {

            System.out.println("Error while retrieving comments from the issue of repo: "  + repo + ":"
                    + ex.getLocalizedMessage());
        }

        return comments;
    }
}
