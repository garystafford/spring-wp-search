package com.example.elasticsearch.service;

import com.example.elasticsearch.model.ElasticsearchPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

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

    public List<ElasticsearchPost> dismaxSearch(String value, int start, int size, float minScore) {

        QueryBuilder queryBuilder = getQueryBuilder(value);

        Client client = elasticsearchTemplate.getClient();
        SearchResponse response = client.prepareSearch()
                .setQuery(queryBuilder)
                .setSize(size)
                .setFrom(start)
                .setMinScore(minScore)
                .addSort("_score", SortOrder.DESC)
                .execute()
                .actionGet();
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

    public long dismaxSearchHits(String value, float minScore) throws ExecutionException, InterruptedException {

        QueryBuilder queryBuilder = getQueryBuilder(value);
        Client client = elasticsearchTemplate.getClient();
        long hits = client.prepareSearch().setQuery(queryBuilder)
                .setMinScore(minScore)
                .execute()
                .get()
                .getHits()
                .totalHits;

        return hits;
    }

    private QueryBuilder getQueryBuilder(String value) {

        value = value.toLowerCase();

        return QueryBuilders.disMaxQuery()
                .add(matchPhraseQuery("post_title", value).boost(3))
                .add(matchPhraseQuery("post_excerpt", value).boost(3))
                .add(matchPhraseQuery("terms.post_tag.name", value).boost(2))
                .add(matchPhraseQuery("terms.category.name", value).boost(2))
                .add(matchPhraseQuery("post_content", value).boost(1));
    }

}
