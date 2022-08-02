package com.example.pdf.controller;

import com.example.pdf.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class BaseController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public String pdf() throws JsonProcessingException {
        newsService.formReport();
        return "/index";
    }
}
