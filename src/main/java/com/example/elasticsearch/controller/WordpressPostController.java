package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.WordpressPost;
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
public class WordpressPostController {

    private Environment env;

    @Autowired
    public WordpressPostController(Environment env) {
        this.env = env;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, List<WordpressPost>> findAll() {

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s&page=%d&per_page=%d", env.getProperty("wordpress.url"), 1, 25);
        ResponseEntity<List<WordpressPost>> wordpressResponse =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<WordpressPost>>() {
                        });
        List<WordpressPost> wordpressPosts = wordpressResponse.getBody();

        Map<String, List<WordpressPost>> wordpressPostMap = new HashMap<>();
        wordpressPostMap.put("WordpressPosts", wordpressPosts);

        return wordpressPostMap;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WordpressPost findById(@PathVariable("id") long id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%d", env.getProperty("wordpress.url"), id);
        ResponseEntity<WordpressPost> wordpressResponse =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<WordpressPost>() {
                        });
        WordpressPost wordpressPost = wordpressResponse.getBody();

        return wordpressPost;
    }

}
