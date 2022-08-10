package com.example.es.service.impl;

import com.example.es.model.entity.News;
import com.example.es.model.mapper.NewsJsonMapper;
import com.example.es.service.ElasticsearchService;
import com.example.es.service.NewsService;
import com.example.es.service.RestTemplateNewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.es.model.entity.RestTemplateParameterData.*;

@Service
public class NewsServiceImpl implements NewsService {
    private RestTemplateNewsService restTemplateService;
    private ElasticsearchService elasticsearchService;

    @Autowired
    public NewsServiceImpl(RestTemplateNewsService restTemplateService, ElasticsearchService elasticsearchService) {
        this.restTemplateService = restTemplateService;
        this.elasticsearchService = elasticsearchService;
    }

    @Override
    @Scheduled(cron = "0 0/30 * * * *")
    public void formData() throws JsonProcessingException {
        Map<String, String> parameters = formParameters();
        String urn = restTemplateService.buildUrn(URN_PART, parameters);
        String json = restTemplateService.sendRequest(urn);
        List<News> newsList = NewsJsonMapper.map(json);
        for(int i = 0; i < newsList.size(); i++) {
            String id = UUID.randomUUID().toString();
            elasticsearchService.updateNews(newsList.get(i), "news", id);
        }

    }

    private Map<String, String> formParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ACCESS_KEY, API_KEY);
        parameters.put(LIMIT, NUMBER_LIMIT);
        parameters.put(LANGUAGES, EN);

        return parameters;
    }
}
