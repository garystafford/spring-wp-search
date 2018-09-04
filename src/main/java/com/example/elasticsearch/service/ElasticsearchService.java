package com.example.elasticsearch.service;

import com.example.elasticsearch.model.ElasticsearchPost;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
                .add(fuzzyQuery("post_title", value).boost(3))
                .add(fuzzyQuery("terms.post_tag.name", value).boost(3))
                .add(fuzzyQuery("post_excerpt", value).boost(2))
                .add(fuzzyQuery("post_content", value).boost(1));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
//        List<ElasticsearchPost> elasticsearchPosts =
//                elasticsearchTemplate.queryForList(searchQuery, ElasticsearchPost.class);


        Client client = elasticsearchTemplate.getClient();
        SearchResponse response = client.prepareSearch().setQuery(queryBuilder).execute().actionGet();
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        ObjectMapper mapper = new ObjectMapper();
        List<ElasticsearchPost> elasticsearchPosts = new ArrayList<>();
        searchHits.forEach(hit -> {
            try {
                elasticsearchPosts.add(mapper.readValue(hit.getSourceAsString(), ElasticsearchPost.class));
                elasticsearchPosts.get(elasticsearchPosts.size() - 1).setScore(hit.getScore());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return elasticsearchPosts;
    }

}
