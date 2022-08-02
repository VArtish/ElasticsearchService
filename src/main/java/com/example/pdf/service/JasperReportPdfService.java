package com.example.pdf.service;

import com.example.pdf.model.entity.News;

import java.util.List;
import java.util.Map;

public interface JasperReportPdfService<T> {
    void exportReport(Map<String, Object> parameters, List<News> dataSource);
}
