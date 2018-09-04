package com.example.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "localhost8681-1", type = "post")
public class ElasticsearchPost implements Serializable {

    @Id
    @JsonProperty("ID")
    private long id;

    @JsonProperty("_score")
    private float score;

    @JsonProperty("post_title")
    private String postTitle;

    @JsonProperty("post_date")
    private String postDate;

    @JsonProperty("post_excerpt")
    private String postExcerpt;

    public ElasticsearchPost setId(long id) {

        this.id = id;
        return this;
    }

    public ElasticsearchPost setScore(float score) {

        this.score = score;
        return this;
    }

    public ElasticsearchPost setPostTitle(String postTitle) {

        this.postTitle = postTitle;
        return this;
    }

    public ElasticsearchPost setPostDate(String postDate) {

        this.postDate = postDate;
        return this;
    }

    public ElasticsearchPost setPostExcerpt(String postExcerpt) {

        this.postExcerpt = postExcerpt;
        return this;
    }
}
