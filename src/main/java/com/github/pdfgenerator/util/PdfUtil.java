package com.github.pdfgenerator.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Arrays;

import static com.lowagie.text.Element.ALIGN_LEFT;

public class PdfUtil {

    private PdfUtil() {
    }

    public static void writeTableHeaderPdf(PdfPTable table, String[] headers) {

        // for auto wide by paper  size
        table.setWidthPercentage(100);

        // cell
        PdfPCell cell = new PdfPCell();

        //  headers
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, getFontContent()));
            table.addCell(cell);
        }

    }

    public static Font getFontTitle() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        return font;
    }

    public static Font getFontSubtitle() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        return font;
    }

    public static Font getFontContent() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10);
        return font;
    }

    public static void enterSpace(Document document) {
        Paragraph space = new Paragraph(" ", getFontSubtitle());
        space.setAlignment(ALIGN_LEFT);
        document.add(space);
    }

}
