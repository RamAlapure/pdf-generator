package com.github.pdfgenerator.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class HeaderFooterPdfPageEventHelper extends PdfPageEventHelper {

    /**
     * The template with the total number of pages.
     */
    PdfTemplate total;

    private Font normal;
    private Font normalSmall;

    public HeaderFooterPdfPageEventHelper() {
        try {
            this.normal = PdfUtil.font(FontFactory.HELVETICA, 8);
            this.normalSmall = PdfUtil.font(FontFactory.HELVETICA, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the PdfTemplate that will hold the total number of pages.
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 12);
    }

    /**
     * Adds a header to every page
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);
        try {
            table.setWidths(new int[]{24, 24, 2});
            table.getDefaultCell().setFixedHeight(10);
            table.getDefaultCell().setBorder(Rectangle.TOP);
            PdfPCell cell = new PdfPCell();
            cell.setBorder(0);
            cell.setBorderWidthTop(1);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPhrase(new Phrase("some text", normalSmall));
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(0);
            cell.setBorderWidthTop(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPhrase(new Phrase(String.format("Page %d of", writer.getPageNumber()), normal));
            table.addCell(cell);

            cell = new PdfPCell(Image.getInstance(total));
            cell.setBorder(0);
            cell.setBorderWidthTop(1);
            table.addCell(cell);
            table.setTotalWidth(document.getPageSize().getWidth()
                    - document.leftMargin() - document.rightMargin());
            table.writeSelectedRows(0, -1, document.leftMargin(),
                    document.bottomMargin() - 15, writer.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    /**
     * Fills out the total number of pages before the document is closed.
     */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1), normal), 2, 2, 0);
    }

}
