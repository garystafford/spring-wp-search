package com.example.elasticsearch.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostWordpress implements Serializable {

    @Id
    private long id;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss")
    @JsonProperty("date_gmt")
    private Date dateGMT;

    private String guidRendered;

    private String titleRendered;


    @JsonProperty("guid")
    private void unpackNestedGuid(Map<String, Object> guid) {

        this.guidRendered = (String) guid.get("rendered");
    }

    @JsonProperty("title")
    private void unpackNestedTitle(Map<String, Object> title) {

        this.titleRendered = (String) title.get("rendered");
    }

}
