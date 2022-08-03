package com.example.pdf.service.impl;

import com.example.pdf.model.entity.News;
import com.example.pdf.service.JasperReportPdfService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JasperReportPdfServiceImpl implements JasperReportPdfService<News> {
    private static final Logger LOGGER = LogManager.getLogger(JasperReportPdfServiceImpl.class);
    private static final String AUTHOR_METADATA = "Artish";
    private static final String ALLOWED_PERMISSION_HINT = "PRINTING";
    private static final String PATH_INPUT_FILE = "src/main/resources/first_report.jrxml";
    private static final String PATH_OUTPUT_FILE = "src/main/resources/";

    public String exportReport(Map<String, Object> parameters, List<News> dataSource) {
        File file = new File(PATH_INPUT_FILE);
        JasperPrint jasperPrint = formJasperPrint(file, parameters, dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        SimpleExporterInput simpleExporterInput = new SimpleExporterInput(jasperPrint);
        exporter.setExporterInput(simpleExporterInput);
        String fileName = UUID.randomUUID().toString();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PATH_OUTPUT_FILE + fileName + ".pdf"));
        exporter.setConfiguration(formPdfExporterConfiguration());
        exporter.setConfiguration(formPdfReportConfiguration());

        try {
            exporter.exportReport();
        } catch (JRException jrException) {
            LOGGER.log(Level.ERROR, jrException.getMessage());
        }

        return fileName;
    }

    private SimplePdfReportConfiguration formPdfReportConfiguration() {
        SimplePdfReportConfiguration reportConfig
                = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        return reportConfig;
    }

    private SimplePdfExporterConfiguration formPdfExporterConfiguration() {
        SimplePdfExporterConfiguration exportConfig
                = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(AUTHOR_METADATA);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint(ALLOWED_PERMISSION_HINT);

        return exportConfig;
    }

    private JasperPrint formJasperPrint(File file, Map<String, Object> parameters, List<News> newsList) {
        JasperPrint jasperPrint = null;
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(newsList);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        } catch (JRException jrException) {
            LOGGER.log(Level.ERROR, jrException.getMessage());
        }
        return jasperPrint;
    }
}
