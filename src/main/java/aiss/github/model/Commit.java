
package aiss.github.model;

import javax.annotation.Generated;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Commit {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("message")
    private String message;

    @JsonProperty("author_name")
    private String author_name;

    @JsonProperty("author_email")
    private String author_email;

    @JsonProperty("authored_date")
    private String authored_date;

    @JsonProperty("committer_name")
    private String committer_name;

    @JsonProperty("committer_email")
    private String committer_email;

    @JsonProperty("committed_date")
    private String committed_date;

    @JsonProperty("commit_info")
    private CommitInfo commitInfo;
    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("commit_info")
    public CommitInfo getCommitInfo() {
        return commitInfo;
    }

    @JsonProperty("commit_info")
    public void setCommitInfo(CommitInfo commitInfo) {
        this.commitInfo = commitInfo;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return commitInfo.getMessage();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return commitInfo.getMessage();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_name() {
        return commitInfo.getAuthor().getName();
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return commitInfo.getAuthor().getEmail();
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthored_date() {
        return commitInfo.getAuthor().getDate();
    }

    public void setAuthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    public String getCommitter_name() {
        return commitInfo.getCommitter().getName();
    }

    public void setCommitter_name(String committer_name) {
        this.committer_name = committer_name;
    }

    public String getCommitter_email() {
        return commitInfo.getCommitter().getEmail();
    }

    public void setCommitter_email(String committer_email) {
        this.committer_email = committer_email;
    }

    public String getCommitted_date() {
        return commitInfo.getCommitter().getDate();
    }

    public void setCommitted_date(String committed_date) {
        this.committed_date = committed_date;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", author_name='" + author_name + '\'' +
                ", author_email='" + author_email + '\'' +
                ", authored_date='" + authored_date + '\'' +
                ", committer_name='" + committer_name + '\'' +
                ", committer_email='" + committer_email + '\'' +
                ", committed_date='" + committed_date + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
