package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.ElasticsearchPost;
import com.example.elasticsearch.repository.ElasticsearchPostRepository;
import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class ElasticsearchPostController {

    private ElasticsearchPostRepository elasticsearchPostRepository;

    public ElasticsearchPostController(ElasticsearchPostRepository elasticsearchPostRepository) {

        this.elasticsearchPostRepository = elasticsearchPostRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<ElasticsearchPost> findAll() {

        Iterable<ElasticsearchPost> elasticsearchPosts = elasticsearchPostRepository.findAll();
        final List content = ((AggregatedPageImpl) elasticsearchPosts).getContent();
        return content;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<ElasticsearchPost> findById(@PathVariable("id") long id) {

        Optional<ElasticsearchPost> elasticsearchPost = elasticsearchPostRepository.findById(id);
        return elasticsearchPost;
    }

}
