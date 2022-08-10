package com.example.pdf.service;

import java.util.Map;

public interface RestTemplateNewsService {
    String sendRequest(String URN);

    String buildUrn(String path, Map<String, String> parameters);
}
