package aiss.github.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "ref_id",
        "title",
        "description",
        "state",
        "created_at",
        "updated_at",
        "closed_at",
        "labels",
        "upvotes",
        "downvotes",
        "author",
        "assignee",
        "comments"
})

@Generated("jsonschema2pojo")
public class IssueResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("ref_id")
    private Integer refId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private Object closedAt;

    @JsonProperty("labels")
    private List<Object> labels;

    @JsonProperty("upvotes")
    private Integer upvotes;

    @JsonProperty("downvotes")
    private Integer downvotes;
    @JsonProperty("assignee")
    private User assignee;
    @JsonProperty("author")
    private User author;
    @JsonProperty("comments")
    private List<Comment> comments;

    public static IssueResponse of(IssueData issue) {
        IssueResponse issueResponse = new IssueResponse();
        issueResponse.setId(issue.getId());
        issueResponse.setRefId(issue.getNumber());
        issueResponse.setTitle(issue.getTitle());
        issueResponse.setDescription(issue.getBody());
        issueResponse.setState(issue.getState());
        issueResponse.setCreatedAt(issue.getCreatedAt());
        issueResponse.setUpdatedAt(issue.getUpdatedAt());
        issueResponse.setClosedAt(issue.getClosedAt());
        issueResponse.setUpvotes(issue.getReactions().getUpvotes());
        issueResponse.setDownvotes(issue.getReactions().getDownvotes());
        issueResponse.setAssignee(User.of(issue.getAssignee()));
        issueResponse.setAuthor(User.of(issue.getUser()));
        return issueResponse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public List<Object> getLabels() {
        return labels;
    }

    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "IssueResponse{" +
                "id=" + id +
                ", refId=" + refId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt=" + closedAt +
                ", labels=" + labels +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", assignee=" + assignee +
                ", author=" + author +
                ", comments=" + comments +
                '}';
    }
}