package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.PostSearchResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostSearchResultRepository extends ElasticsearchRepository<PostSearchResult, String> {
}
