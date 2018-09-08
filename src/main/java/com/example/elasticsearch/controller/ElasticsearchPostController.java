package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.ElasticsearchPost;
import com.example.elasticsearch.repository.ElasticsearchPostRepository;
import com.example.elasticsearch.service.ElasticsearchService;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/elastic", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
public class ElasticsearchPostController {

    private ElasticsearchPostRepository elasticsearchPostRepository;
    private ElasticsearchService elasticsearchService;

    public ElasticsearchPostController(ElasticsearchPostRepository elasticsearchPostRepository,
                                       ElasticsearchService elasticsearchService) {

        this.elasticsearchPostRepository = elasticsearchPostRepository;
        this.elasticsearchService = elasticsearchService;
    }

    @RequestMapping(value = "/")
    public Map<String, List<ElasticsearchPost>> findAll() {

        Iterable<ElasticsearchPost> elasticsearchPosts = elasticsearchPostRepository.findAll();
        final List content = ((AggregatedPageImpl) elasticsearchPosts).getContent();
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", content);

        return elasticsearchPostMap;
    }

    @RequestMapping(value = "/{id}")
    public Optional<ElasticsearchPost> findById(@PathVariable("id") long id) {

        Optional<ElasticsearchPost> elasticsearchPost = elasticsearchPostRepository.findById(id);

        return elasticsearchPost;
    }

    @RequestMapping(value = "/simple-search")
    public Map<String, List<ElasticsearchPost>> fieldContains(@RequestParam("field") String field,
                                                              @RequestParam("value") String value) {

        List<ElasticsearchPost> elasticsearchPosts = elasticsearchService.fieldContains(field, value);
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", elasticsearchPosts);

        return elasticsearchPostMap;
    }

    @RequestMapping(value = "/dismax-search")
    public Map<String, List<ElasticsearchPost>> dismaxSearch(@RequestParam("value") String value) {

        List<ElasticsearchPost> elasticsearchPosts = elasticsearchService.dismaxSearch(value);
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", elasticsearchPosts);

        return elasticsearchPostMap;
    }

    @RequestMapping(value = "/dismax-search/hits")
    public long dismaxSearchHits(@RequestParam("value") String value) throws ExecutionException, InterruptedException {

        long hits = elasticsearchService.dismaxSearchHits(value);

        return hits;
    }


}
