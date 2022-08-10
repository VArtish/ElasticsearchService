package com.example.pdf.service.impl;

import com.example.pdf.model.entity.News;
import com.example.pdf.model.mapper.NewsJsonMapper;
import com.example.pdf.service.NewsService;
import com.example.pdf.service.RestTemplateNewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.pdf.model.entity.RestTemplateParameterData.*;

@Service
public class NewsServiceImpl implements NewsService {
    private RestTemplateNewsService restTemplateService;

    @Autowired
    public NewsServiceImpl(RestTemplateNewsService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Override
    public String formReport() throws JsonProcessingException {
        Map<String, String> parameters = formParameters();
        String urn = restTemplateService.buildUrn(URN_PART, parameters);
        String json = restTemplateService.sendRequest(urn);
        List<News> newsList = NewsJsonMapper.map(json);

        return "";
    }

    private Map<String, String> formParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ACCESS_KEY, API_KEY);
        parameters.put(LIMIT, NUMBER_LIMIT);
        parameters.put(LANGUAGES, EN);

        return parameters;
    }
}
