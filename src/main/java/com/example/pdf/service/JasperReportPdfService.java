package com.example.pdf.service;

import com.example.pdf.model.entity.News;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.util.List;
import java.util.Map;

public interface JasperReportPdfService<T> {
    String exportReport(Map<String, Object> parameters, List<News> dataSource);
}
