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
    private String title;

    @JsonProperty("post_date")
    private String publishDate;

    @JsonProperty("post_excerpt")
    private String excerpt;

    @JsonProperty("guid")
    private String url;

    public ElasticsearchPost setId(long id) {

        this.id = id;
        return this;
    }

    public ElasticsearchPost setScore(float score) {

        this.score = score;
        return this;
    }

    public ElasticsearchPost setTitle(String title) {

        this.title = title;
        return this;
    }

    public ElasticsearchPost setPublishDate(String publishDate) {

        this.publishDate = publishDate;
        return this;
    }

    public ElasticsearchPost setExcerpt(String excerpt) {

        this.excerpt = excerpt;
        return this;
    }

    public ElasticsearchPost setUrl(String url) {

        this.url = url;
        return this;
    }
}
