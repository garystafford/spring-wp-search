package com.example.elasticsearch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticConfig {

    private final ElasticsearchOperations operations;

    @Autowired
    public ElasticConfig(ElasticsearchOperations operations) {

        this.operations = operations;
    }


}
