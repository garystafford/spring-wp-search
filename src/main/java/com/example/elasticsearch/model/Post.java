package com.example.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "localhost8681-1", type = "post")
public class Post {

    @Id
    private long _id;

    private String post_title;

    private String post_content;

    private Date post_date;

    private String post_excerpt;

    private String guid;

    @JsonProperty("post_author.display_name")
    private String post_author;
}
