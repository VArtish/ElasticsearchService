package com.example.pdf.service;

import com.example.pdf.model.entity.News;

import java.util.List;

public interface ElasticsearchService {
    List<News> findNewsByTitle(String partTitle);

    void updateNews(News news, String indexName, String id);
}
