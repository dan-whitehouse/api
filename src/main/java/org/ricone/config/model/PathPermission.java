package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/* Example JSON
[
  {
    "path": "/requests/xLeas/**",
    "get": false,
    "post": false,
    "put": true,
    "delete": false,
    "head": false
  },
  {
    "path": "/requests/xSchools",
    "get": false,
    "post": false,
    "put": true,
    "delete": false,
    "head": false
  },
  {
    "path": "/requests/xCourses/",
    "get": false,
    "post": false,
    "put": true,
    "delete": false,
    "head": false
  }
]
*/

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"path", "get", "post", "put", "delete", "head"})
public class PathPermission implements Serializable {

    @JsonProperty("path")
    private String path;
    @JsonProperty("get")
    private Boolean get;
    @JsonProperty("post")
    private Boolean post;
    @JsonProperty("put")
    private Boolean put;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("head")
    private Boolean head;
    private final static long serialVersionUID = -392240296863581825L;

    /**
     * No args constructor for use in serialization
     */
    public PathPermission() {
    }

    /**
     * @param put
     * @param post
     * @param get
     * @param delete
     * @param path
     * @param head
     */
    public PathPermission(String path, Boolean get, Boolean post, Boolean put, Boolean delete, Boolean head) {
        super();
        this.path = path;
        this.get = get;
        this.post = post;
        this.put = put;
        this.delete = delete;
        this.head = head;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("get")
    public Boolean getGet() {
        if(get == null) {
            return false;
        }
        return get;
    }

    @JsonProperty("get")
    public void setGet(Boolean get) {
        this.get = get;
    }

    @JsonProperty("post")
    public Boolean getPost() {
        if(get == null) {
            return false;
        }
        return post;
    }

    @JsonProperty("post")
    public void setPost(Boolean post) {
        this.post = post;
    }

    @JsonProperty("put")
    public Boolean getPut() {
        if(put == null) {
            return false;
        }
        return put;
    }

    @JsonProperty("put")
    public void setPut(Boolean put) {
        this.put = put;
    }

    @JsonProperty("delete")
    public Boolean getDelete() {
        if(delete == null) {
            return false;
        }
        return delete;
    }

    @JsonProperty("delete")
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @JsonProperty("head")
    public Boolean getHead() {
        if(head == null) {
            return false;
        }
        return head;
    }

    @JsonProperty("head")
    public void setHead(Boolean head) {
        this.head = head;
    }
}