package com.github.pdfgenerator.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.lowagie.text.Element.ALIGN_CENTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PdfUtilTest {


    @Test
    public void testFontWhenGivenFontSizeThenReturnFontWithCorrectSize() {
        // Arrange
        String fontName = FontFactory.HELVETICA;
        int fontSize = 12;

        // Act
        Font font = PdfUtil.font(fontName, fontSize);

        // Assert
        assertEquals(fontSize, font.getSize(), "Font size should be set correctly");
    }

    @Test
    public void testFontWhenGivenFontSizeThenReturnNotNullFont() {
        // Arrange
        String fontName = FontFactory.HELVETICA;
        int fontSize = 12;

        // Act
        Font font = PdfUtil.font(fontName, fontSize);

        // Assert
        assertNotNull(font, "Font should not be null");
    }

    @Test
    public void testParagraphWhenGivenValidStringFontAndAlignmentThenAddParagraphToDocument() throws DocumentException, IOException {
        // Arrange
        Document document = new Document();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);
        document.open();
        String content = "Test content";
        PdfUtil.paragraph(content, PdfUtil.FONT_TITLE, ALIGN_CENTER, document);
        document.close();

        // Assert
        PdfReader pdfReader = new PdfReader(stream.toByteArray());
        PdfTextExtractor textExtractor = new PdfTextExtractor(pdfReader);
        assertEquals(content, textExtractor.getTextFromPage(1));
    }

}