package com.github.pdfgenerator.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

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
        return font(FontFactory.HELVETICA_BOLD, 18);
    }

    public static Font getFontSubtitle() {
        return font(FontFactory.HELVETICA, 12);
    }

    public static Font getFontContent() {
        return font(FontFactory.HELVETICA, 10);
    }

    public static Font font(String helvetica, int size) {
        Font font = FontFactory.getFont(helvetica);
        font.setSize(size);
        return font;
    }

    public static void enterSpace(Document document) {
        Paragraph space = new Paragraph(" ", getFontSubtitle());
        space.setAlignment(ALIGN_LEFT);
        document.add(space);
    }

}
