package com.example.elasticsearch.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class SpringConfig {

    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

//    @Bean
//    public Client client() {
//        Settings elasticsearchSettings = Settings.builder()
//                .put("client.transport.sniff", true)
//                .put("cluster.name", environment.getProperty("spring.data.elasticsearch.cluster-name"))
//                .build();
//        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
//        try {
//            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9202));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return client;
//    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }
}
