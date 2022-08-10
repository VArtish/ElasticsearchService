package com.example.es.controller;

import com.example.es.service.impl.ElasticsearchServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class BaseController {
    @Autowired
    private ElasticsearchServiceImpl elasticsearchService;

    @GetMapping
    public String pdf() throws JsonProcessingException {
        elasticsearchService.findNewsByTitle("asd1 123");
        return "bruh";
    }
}
