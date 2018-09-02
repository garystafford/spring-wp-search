package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.ElasticsearchPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchPostRepository extends ElasticsearchRepository<ElasticsearchPost, Long> {

}
