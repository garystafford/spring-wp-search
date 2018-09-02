package com.example.elasticsearch.controller;

import com.example.elasticsearch.model.PostWordpress;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/wordpress")
public class PostWordpressController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PostWordpress> findAll() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PostWordpress>> wordpressResponse =
                restTemplate.exchange("http://localhost:8681/?rest_route=/wp/v2/posts",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PostWordpress>>() {
                        });
        List<PostWordpress> postWordpressList = wordpressResponse.getBody();
//        System.out.print(postWordpressList.toString());
        return postWordpressList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostWordpress findById(@PathVariable("id") long id) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PostWordpress> wordpressResponse =
                restTemplate.exchange(String.format("http://localhost:8681/?rest_route=/wp/v2/posts/%d", id),
                        HttpMethod.GET, null, new ParameterizedTypeReference<PostWordpress>() {
                        });
        PostWordpress postWordpress = wordpressResponse.getBody();
//        System.out.print(postWordpress.toString());
        return postWordpress;
    }

}
