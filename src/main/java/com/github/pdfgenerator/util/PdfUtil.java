package com.github.pdfgenerator.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import static com.lowagie.text.Element.ALIGN_LEFT;

public class PdfUtil {

    public static final Font FONT_TITLE = font(FontFactory.HELVETICA_BOLD, 18);
    public static final Font FONT_SUBTITLE = font(FontFactory.HELVETICA, 12);
    public static final Font FONT_CONTENT = font(FontFactory.HELVETICA, 10);
    public static final Font FONT_FOOTER = font(FontFactory.HELVETICA, 8);

    private PdfUtil() {
    }

    public static void writeTableHeaderPdf(PdfPTable table, String[] headers) {

        // for auto wide by paper  size
        table.setWidthPercentage(100);

        // cell
        PdfPCell cell = new PdfPCell();

        //  headers
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, FONT_CONTENT));
            table.addCell(cell);
        }

    }

    public static Font font(String helvetica, int size) {
        Font font = FontFactory.getFont(helvetica);
        font.setSize(size);
        return font;
    }

    public static void space(Document document) {
        Paragraph space = new Paragraph(" ", FONT_SUBTITLE);
        space.setAlignment(ALIGN_LEFT);
        document.add(space);
    }

    public static void paragraph(String string, Font font, int alignment, Document document) {
        Paragraph paragraph = new Paragraph(string, font);
        paragraph.setAlignment(alignment);
        document.add(paragraph);
    }
}
