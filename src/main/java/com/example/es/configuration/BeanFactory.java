package com.example.es.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class BeanFactory {
    private static final String NEWS_URL = "http://api.mediastack.com";

    @Bean
    public RestTemplate restTemplateNews() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(NEWS_URL));
        return restTemplate;
    }

    @Bean
    public ElasticsearchClient restTemplateElasticsearch() {
        RestClient httpClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        JacksonJsonpMapper jsonMapper = new JacksonJsonpMapper();
        ElasticsearchTransport elasticsearchTransport = new RestClientTransport(httpClient, jsonMapper);
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(elasticsearchTransport);

        return elasticsearchClient;
    }
}