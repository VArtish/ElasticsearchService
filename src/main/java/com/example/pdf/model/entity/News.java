package com.example.pdf.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class News {
    private String title;
    private String description;
    private String author;
    private String source;
    private String country;
    private String image;
    private String category;
    @JsonProperty("published_at")
    private String date;

}
