package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.WordPressPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wordpress")
public class WordPressPostController {

    private Environment environment;
    private RestTemplate restTemplate;

    @Autowired
    public WordPressPostController(Environment environment, RestTemplate restTemplate) {

        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, List<WordPressPost>> findAll() {

        restTemplate = new RestTemplate();
        String url = String.format("%s&per_page=%s",
                environment.getProperty("wordpress.url"),
                environment.getProperty("wordpress.per-page"));
        ResponseEntity<List<WordPressPost>> wordPressResponse =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<WordPressPost>>() {
                        });
        List<WordPressPost> wordPressPosts = wordPressResponse.getBody();

        Map<String, List<WordPressPost>> wordPressPostMap = new HashMap<>();
        wordPressPostMap.put("WordPressPosts", wordPressPosts);

        return wordPressPostMap;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WordPressPost findById(@PathVariable("id") long id) {

        restTemplate = new RestTemplate();
        String url = String.format("%s/%d",
                environment.getProperty("wordpress.url"),
                id);
        ResponseEntity<WordPressPost> wordpressResponse =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<WordPressPost>() {
                        });
        WordPressPost wordpressPost = wordpressResponse.getBody();

        return wordpressPost;
    }
}
