package com.example.pdf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public interface NewsService {
    String formReport() throws JsonProcessingException;
}
