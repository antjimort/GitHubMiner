
package aiss.github.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Project {

    @JsonProperty("owner_url")
    private String ownerUrl;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("columns_url")
    private String columnsUrl;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("body")
    private String body;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("state")
    private String state;
    @JsonProperty("creator")
    private Creator creator;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("organization_permission")
    private String organizationPermission;
    @JsonProperty("private")
    private Boolean _private;

    @JsonProperty("owner_url")
    public String getOwnerUrl() {
        return ownerUrl;
    }

    @JsonProperty("owner_url")
    public void setOwnerUrl(String ownerUrl) {
        this.ownerUrl = ownerUrl;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonProperty("columns_url")
    public String getColumnsUrl() {
        return columnsUrl;
    }

    @JsonProperty("columns_url")
    public void setColumnsUrl(String columnsUrl) {
        this.columnsUrl = columnsUrl;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("creator")
    public Creator getCreator() {
        return creator;
    }

    @JsonProperty("creator")
    public void setCreator(Creator creator) {
        this.creator = creator;
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

    @JsonProperty("organization_permission")
    public String getOrganizationPermission() {
        return organizationPermission;
    }

    @JsonProperty("organization_permission")
    public void setOrganizationPermission(String organizationPermission) {
        this.organizationPermission = organizationPermission;
    }

    @JsonProperty("private")
    public Boolean getPrivate() {
        return _private;
    }

    @JsonProperty("private")
    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    @Override
    public String toString() {
        return "Project{" +
                "ownerUrl='" + ownerUrl + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", columnsUrl='" + columnsUrl + '\'' +
                ", id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", creator=" + creator +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", organizationPermission='" + organizationPermission + '\'' +
                ", _private=" + _private +
                '}';
    }
}