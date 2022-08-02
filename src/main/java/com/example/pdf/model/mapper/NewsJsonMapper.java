package com.example.pdf.model.mapper;

import com.example.pdf.model.entity.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.pdf.model.entity.JsonParameterData.*;

public class NewsJsonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<News> map(String json) throws JsonProcessingException {
        JsonNode tree = mapper.readTree(json);
        JsonNode dataNode = tree.get("data");
        Iterator<JsonNode> elements = dataNode.elements();
        List<News> newsList = new ArrayList<>();

        while(elements.hasNext()) {
            JsonNode jsonNode = elements.next();
            String author = jsonNode.get(AUTHOR).asText();
            String category = jsonNode.get(CATEGORY).asText();
            String source = jsonNode.get(SOURCE).asText();
            String description = jsonNode.get(DESCRIPTION).asText();
            String image = jsonNode.get(IMAGE).asText();
            String country = jsonNode.get(COUNTRY).asText();
            String date = jsonNode.get(DATE).asText();
            String title = jsonNode.get(TITLE).asText();
            News news = new News(title, description, author, source, country, image, category, date);
            newsList.add(news);
        }

        return newsList;
    }
}
