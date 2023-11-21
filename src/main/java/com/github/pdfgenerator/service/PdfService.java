package com.github.pdfgenerator.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface PdfService {

    void getDocument(HttpServletResponse response) throws IOException;
}
