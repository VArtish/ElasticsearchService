package com.example.pdf.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.example.pdf.model.entity.News;
import com.example.pdf.service.ElasticsearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private final ElasticsearchClient elasticsearchClient;
    private static final Logger LOGGER = LogManager.getLogger(ElasticsearchServiceImpl.class);

    public ElasticsearchServiceImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @Override
    public List<News> findNewsByTitle(String partTitle) {
        List<News> newsList = new ArrayList<>();
        try {
            SearchResponse<News> response = elasticsearchClient.search(s -> s
                            .index("news")
                            .query(q -> q
                                    .multiMatch(t -> t
                                            .fields("title", "text")
                                            .query(partTitle)
                                    )
                            ),
                    News.class
            );

            List<Hit<News>> hits = response.hits().hits();
            for (Hit<News> hit: hits) {
                News news = hit.source();
                newsList.add(news);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        return newsList;
    }

    @Override
    public void updateNews(News news, String indexName, String id) {
        try {
            IndexRequest<News> indexRequest = IndexRequest.of(i -> i.index("news")
                    .id(id)
                    .document(news)
            );
            elasticsearchClient.index(indexRequest);
        } catch(IOException ioException) {
            LOGGER.log(Level.ERROR, ioException.getMessage());
        }
    }
}
