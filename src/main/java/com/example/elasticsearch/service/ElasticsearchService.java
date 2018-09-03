package com.example.elasticsearch.service;

import com.example.elasticsearch.model.ElasticsearchPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

@Component
public class ElasticsearchService {

    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public ElasticsearchService(ElasticsearchTemplate elasticsearchTemplate) {

        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public List<ElasticsearchPost> customQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery("post_title", ".*azure.*"))
                .build();
        List<ElasticsearchPost> elasticsearchPosts = elasticsearchTemplate.queryForList(searchQuery, ElasticsearchPost.class);
        return elasticsearchPosts;
    }
}
