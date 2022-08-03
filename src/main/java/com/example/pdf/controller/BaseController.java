package com.example.pdf.controller;

import com.example.pdf.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.LogManager;

@RestController
@RequestMapping("/pdf")
public class BaseController {
    @Autowired
    private NewsService newsService;
    private static final String PATH = "src/main/resources/";

    @GetMapping
    public ResponseEntity<byte[]> pdf() throws JsonProcessingException {
        String fileName = newsService.formReport() + ".pdf";
        ResponseEntity<byte[]> response = null;
        try {
            byte[] array = Files.readAllBytes(Paths.get(PATH + fileName));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            ;
            headers.setContentDispositionFormData(fileName, fileName);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<>(array, headers, HttpStatus.OK);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return response;
    }
}
