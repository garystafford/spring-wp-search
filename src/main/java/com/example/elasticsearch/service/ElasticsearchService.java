package com.example.elasticsearch.service;

import com.example.elasticsearch.model.ElasticsearchPost;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;

@Component
public class ElasticsearchService {

    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public ElasticsearchService(ElasticsearchTemplate elasticsearchTemplate) {

        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public List<ElasticsearchPost> fieldContains(String field, String value) {

        field = field.toLowerCase();
        value = value.toLowerCase();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(fuzzyQuery(field, value))
                .build();
        List<ElasticsearchPost> elasticsearchPosts =
                elasticsearchTemplate.queryForList(searchQuery, ElasticsearchPost.class);
        return elasticsearchPosts;
    }

    public List<ElasticsearchPost> dismaxSearch(String value) {

        value = value.toLowerCase();

        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
                .add(fuzzyQuery("post_title", value).boost(2))
                .add(fuzzyQuery("terms.post_tag.name", value).boost(2))
                .add(fuzzyQuery("post_excerpt", value).boost(1))
                .add(fuzzyQuery("post_content", value).boost(0));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        List<ElasticsearchPost> elasticsearchPosts =
                elasticsearchTemplate.queryForList(searchQuery, ElasticsearchPost.class);

        return elasticsearchPosts;
    }

}
