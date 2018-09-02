package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.ElasticsearchPost;
import com.example.elasticsearch.repository.ElasticsearchPostRepository;
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
    public Map<String, List<ElasticsearchPost>> findAll() {

        Iterable<ElasticsearchPost> elasticsearchPosts = elasticsearchPostRepository.findAll();
        final List content = ((AggregatedPageImpl) elasticsearchPosts).getContent();
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", content);

        return elasticsearchPostMap;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<ElasticsearchPost> findById(@PathVariable("id") long id) {

        Optional<ElasticsearchPost> elasticsearchPost = elasticsearchPostRepository.findById(id);

        return elasticsearchPost;
    }

}
