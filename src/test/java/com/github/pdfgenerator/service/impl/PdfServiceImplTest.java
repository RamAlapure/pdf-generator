package com.github.pdfgenerator.service.impl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PdfServiceImplTest {

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private PdfServiceImpl pdfService;

    @BeforeEach
    public void setUp() throws IOException {
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(response.getOutputStream()).thenReturn(outputStream);
    }

    @Test
    public void testGetDocumentWhenCalledThenPdfCreated() throws IOException {
        // Act
        pdfService.getDocument(response);

        // Assert
        verify(response, times(1)).getOutputStream();
    }

}