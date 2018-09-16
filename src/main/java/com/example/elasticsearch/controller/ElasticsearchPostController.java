package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.ElasticsearchPost;
import com.example.elasticsearch.repository.ElasticsearchPostRepository;
import com.example.elasticsearch.service.ElasticsearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/elastic", method = RequestMethod.GET)
@Api(value = "search", description = "Operations pertaining to searching the Elasticsearch index")
public class ElasticsearchPostController {

    private ElasticsearchPostRepository elasticsearchPostRepository;
    private ElasticsearchService elasticsearchService;

    public ElasticsearchPostController(ElasticsearchPostRepository elasticsearchPostRepository,
                                       ElasticsearchService elasticsearchService) {

        this.elasticsearchPostRepository = elasticsearchPostRepository;
        this.elasticsearchService = elasticsearchService;
    }

    /**
     * Returns a list of all posts
     *
     * @return list of posts
     */
    @RequestMapping(value = "/")
    @ApiOperation(value = "Returns indexed posts")
    public Map<String, List<ElasticsearchPost>> findAll() {

        Iterable<ElasticsearchPost> elasticsearchPosts = elasticsearchPostRepository.findAll();
        final List content = ((AggregatedPageImpl) elasticsearchPosts).getContent();
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", content);

        return elasticsearchPostMap;
    }

    /**
     * Performs search for post with unique id
     *
     * @param id the unique id of the post
     * @return single post
     */
    @RequestMapping(value = "/{id}")
    @ApiOperation(value = "Returns a post by id")
    public Map<String, Optional<ElasticsearchPost>> findById(@PathVariable("id") long id) {

        Optional<ElasticsearchPost> elasticsearchPost = elasticsearchPostRepository.findById(id);
        Map<String, Optional<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", elasticsearchPost);

        return elasticsearchPostMap;
    }

    /**
     * Performs search within the field input with value input
     *
     * @param field the field to search
     * @param value the string to search for
     * @return list of posts
     */
    @RequestMapping(value = "/simple-search")
    @ApiOperation(value = "Performs search within the field input with value input")
    public Map<String, List<ElasticsearchPost>> fieldContains(@RequestParam("field") String field,
                                                              @RequestParam("value") String value) {

        List<ElasticsearchPost> elasticsearchPosts = elasticsearchService.fieldContains(field, value);
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", elasticsearchPosts);

        return elasticsearchPostMap;
    }

    /**
     * Performs dismax search and returns a list of posts containing the value
     *
     * @param value    the string to search for
     * @param start    the starting index
     * @param size     the number of results to return
     * @param minScore the minimum score to return
     * @return list of posts
     */
    @RequestMapping(value = "/dismax-search")
    @ApiOperation(value = "Performs dismax search and returns a list of posts containing the value input")
    public Map<String, List<ElasticsearchPost>> dismaxSearch(@RequestParam("value") String value,
                                                             @RequestParam("start") int start,
                                                             @RequestParam("size") int size,
                                                             @RequestParam("minScore") float minScore) {

        List<ElasticsearchPost> elasticsearchPosts = elasticsearchService.dismaxSearch(value, start, size, minScore);
        Map<String, List<ElasticsearchPost>> elasticsearchPostMap = new HashMap<>();
        elasticsearchPostMap.put("ElasticsearchPosts", elasticsearchPosts);

        return elasticsearchPostMap;
    }

    /**
     * Performs dismax search and returns a count of posts containing the value
     *
     * @param value    the string to search for
     * @param minScore the minimum score to return
     * @return count of posts
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/dismax-search/hits")
    @ApiOperation(value = "Performs dismax search and returns count of posts containing the value input")
    public long dismaxSearchHits(@RequestParam("value") String value,
                                 @RequestParam("minScore") float minScore) throws ExecutionException, InterruptedException {

        long hits = elasticsearchService.dismaxSearchHits(value, minScore);

        return hits;
    }
}
