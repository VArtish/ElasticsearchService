package com.example.pdf.service.impl;

import com.example.pdf.service.RestTemplateNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class RestTemplateNewsServiceImpl implements RestTemplateNewsService {
    private RestTemplate restTemplate;

    public RestTemplateNewsServiceImpl(@Qualifier("restTemplateNews") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String sendRequest(String URN) {
        String response = restTemplate.getForObject(URN, String.class);
        return response;
    }

    @Override
    public String buildUrn(String path, Map<String, String> parameters) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(path);
        for (String key : parameters.keySet()) {
            uriBuilder.queryParam(key, parameters.get(key));
        }

        UriComponents uriComponents = uriBuilder.build();
        return uriComponents.toString();
    }
}
