package com.example.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "localhost8681-1", type = "post")
public class PostSearchResult implements Serializable {

    @Id
    private String _id;

    @JsonProperty("post_title")
    private String postTitle;

    @JsonProperty("post_date")
    private String postDate;

    @JsonProperty("post_excerpt")
    private String postExcerpt;

    public PostSearchResult set_id(String _id) {

        this._id = _id;
        return this;
    }

    public PostSearchResult setPostTitle(String postTitle) {

        this.postTitle = postTitle;
        return this;
    }

    public PostSearchResult setPostDate(String postDate) {

        this.postDate = postDate;
        return this;
    }

    public PostSearchResult setPostExcerpt(String postExcerpt) {

        this.postExcerpt = postExcerpt;
        return this;
    }
}
