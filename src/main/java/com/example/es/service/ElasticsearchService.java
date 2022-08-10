package com.example.es.service;

import com.example.es.model.entity.News;

import java.util.List;

public interface ElasticsearchService {
    List<News> findNewsByTitle(String partTitle);

    void updateNews(News news, String indexName, String id);
}
