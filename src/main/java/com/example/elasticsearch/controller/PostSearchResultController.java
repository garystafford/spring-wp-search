package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.PostElasticSearch;
import com.example.elasticsearch.repository.PostSearchResultRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class PostSearchResultController {

    private PostSearchResultRepository postSearchResultRepository;

    public PostSearchResultController(PostSearchResultRepository postSearchResultRepository) {

        this.postSearchResultRepository = postSearchResultRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<PostElasticSearch> findAll() {

        return postSearchResultRepository.findAll();
    }

}
