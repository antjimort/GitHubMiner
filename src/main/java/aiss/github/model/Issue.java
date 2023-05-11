package aiss.github.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "number",
        "title",
        "body",
        "state",
        "created_at",
        "updated_at",
        "closed_at",
        "labels",
        "user",
        "assignee",
        "upvotes",
        "downvotes",
        "html_url",
        "commentList"
})

@Generated("jsonschema2pojo")
public class Issue {

    @JsonIgnore
    private String url;
    @JsonIgnore
    private String repositoryUrl;
    @JsonIgnore
    private String labelsUrl;
    @JsonIgnore
    private String commentsUrl;
    @JsonIgnore
    private String eventsUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("id")
    private Integer id;
    @JsonIgnore
    private String nodeId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("title")
    private String title;
    @JsonProperty("user")
    private User user;
    @JsonProperty("labels")
    private List<Object> labels;
    @JsonProperty("state")
    private String state;
    @JsonIgnore
    private Boolean locked;
    @JsonProperty("assignee")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private User assignee;
    @JsonIgnore
    private List<Object> assignees;
    @JsonIgnore
    private Object milestone;
    @JsonIgnore
    private Integer comments;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private Object closedAt;
    @JsonIgnore
    private String authorAssociation;
    @JsonIgnore
    private Object activeLockReason;
    @JsonIgnore
    private Boolean draft;
    @JsonIgnore
    private PullRequest pullRequest;
    @JsonProperty("body")
    private String body;

    @JsonIgnore
    private Object closedBy;

    @JsonIgnore
    @JsonProperty("reactions")
    private Reactions reactions;

    @JsonIgnore
    private String timelineUrl;
    @JsonIgnore
    private Object performedViaGithubApp;
    @JsonIgnore
    private Object stateReason;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    private Integer upvotes;
    private Integer downvotes;
    private List<Comment> commentsList;

    public static Issue of(Issue i) {
        Issue parsedIssue = new Issue();
        parsedIssue.setId(i.getId());
        parsedIssue.setNumber(i.getNumber());
        parsedIssue.setTitle(i.getTitle());
        parsedIssue.setBody(i.getBody());
        parsedIssue.setState(i.getState());
        parsedIssue.setCreatedAt(i.getCreatedAt());
        parsedIssue.setUpdatedAt(i.getUpdatedAt());
        parsedIssue.setLabels(i.getLabels());
        parsedIssue.setUpvotes(i.getReactions().getUpvotes());
        parsedIssue.setDownvotes(i.getReactions().getDownvotes());
        parsedIssue.setUser(User.of(i.getUser()));
        parsedIssue.setAssignee(User.of(i.getAssignee()));
        parsedIssue.setHtmlUrl(i.getHtmlUrl());
        return parsedIssue;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getRepositoryUrl() {
        return repositoryUrl;
    }


    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }


    public String getLabelsUrl() {
        return labelsUrl;
    }


    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }


    public String getCommentsUrl() {
        return commentsUrl;
    }


    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }


    public String getEventsUrl() {
        return eventsUrl;
    }


    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getNodeId() {
        return nodeId;
    }


    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("labels")
    public List<Object> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }


    public Boolean getLocked() {
        return locked;
    }


    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @JsonProperty("assignee")
    public User getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }


    public List<Object> getAssignees() {
        return assignees;
    }


    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }


    public Object getMilestone() {
        return milestone;
    }


    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }


    public Integer getComments() {
        return comments;
    }


    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public Object getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }


    public String getAuthorAssociation() {
        return authorAssociation;
    }


    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }


    public Object getActiveLockReason() {
        return activeLockReason;
    }


    public void setActiveLockReason(Object activeLockReason) {
        this.activeLockReason = activeLockReason;
    }


    public Boolean getDraft() {
        return draft;
    }


    public void setDraft(Boolean draft) {
        this.draft = draft;
    }


    public PullRequest getPullRequest() {
        return pullRequest;
    }


    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }


    public Object getClosedBy() {
        return closedBy;
    }


    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }


    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    public String getTimelineUrl() {
        return timelineUrl;
    }

    public void setTimelineUrl(String timelineUrl) {
        this.timelineUrl = timelineUrl;
    }

    public Object getPerformedViaGithubApp() {
        return performedViaGithubApp;
    }

    public void setPerformedViaGithubApp(Object performedViaGithubApp) {
        this.performedViaGithubApp = performedViaGithubApp;
    }


    public Object getStateReason() {
        return stateReason;
    }

    public void setStateReason(Object stateReason) {
        this.stateReason = stateReason;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
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

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "Issue{" +
                ", id=" + id +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt=" + closedAt +
                ", labels=" + labels +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", user=" + user +
                ", assignee=" + assignee +
                ", commentsList=" + commentsList +
                '}';
    }
}
