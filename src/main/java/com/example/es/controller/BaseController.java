package com.example.es.controller;

import com.example.es.model.entity.News;
import com.example.es.service.impl.ElasticsearchServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elasticsearch")
public class BaseController {
    @Autowired
    private ElasticsearchServiceImpl elasticsearchService;

    @GetMapping
    public List<News> pdf(@RequestParam("title") String searchLine) throws JsonProcessingException {
        List<News> newsList = elasticsearchService.findNewsByTitle(searchLine);
        return newsList;
    }
}
