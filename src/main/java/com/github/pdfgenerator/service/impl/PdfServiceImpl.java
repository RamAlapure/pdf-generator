package com.github.pdfgenerator.service.impl;

import com.github.pdfgenerator.model.User;
import com.github.pdfgenerator.service.PdfService;
import com.github.pdfgenerator.util.HeaderFooterPdfPageEventHelper;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.pdfgenerator.util.PdfUtil.*;
import static com.lowagie.text.Element.*;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public void getDocument(HttpServletResponse response) throws IOException {

        try (Document document = new Document(PageSize.A4)) {
            // define paper size
            PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
            HeaderFooterPdfPageEventHelper pageEventHelper = new HeaderFooterPdfPageEventHelper();
            pdfWriter.setPageEvent(pageEventHelper);
            // start document
            document.open();

            // title
            Paragraph title = new Paragraph("Report User", getFontTitle());
            title.setAlignment(ALIGN_CENTER);
            document.add(title);

            // subtitel
            Paragraph subtitel = new Paragraph("Report Date : 09/12/2022", getFontSubtitle());
            subtitel.setAlignment(ALIGN_LEFT);
            document.add(subtitel);

            enterSpace(document);

            // table header
            String[] headers = new String[]{"No", "username", "Roles", "Permission", "Active", "Bloked", "Created By", "Created Date", "Update By", "Update Date"};
            PdfPTable table = new PdfPTable(10);
            table.setHeaderRows(1);
            writeTableHeaderPdf(table, headers);
            document.add(table);

            writeTableData(table);
            document.add(table);

            HeaderFooter footer = new HeaderFooter(new Phrase(String.valueOf(document.getPageNumber()), getFontContent()), true);
            document.add(footer);
        }
    }

    public void writeTableData(PdfPTable table) {
        List<User> list = getUserList();

        // cell
        PdfPCell cell = new PdfPCell();
        int number = 0;
        for (User item : list) {
            number += 1;
            cell.setPhrase(new Phrase(String.valueOf(number), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getUsername(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getRoles(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getPermissions(), getFontContent()));
            table.addCell(cell);

            String active = item.getActive() == 1 ? "Active" : "Non Active";
            cell.setPhrase(new Phrase(active, getFontContent()));
            table.addCell(cell);

            String blocked = item.getBlocked() == 1 ? "Blocked" : "Non Blocked";
            cell.setPhrase(new Phrase(blocked, getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getCreatedBy(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getCreatedDate().toString(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getUpdatedBy(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getUpdatedDate().toString(), getFontContent()));
            table.addCell(cell);
        }
    }

    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1L).username("admin").password("*****").roles("ADMIN,USER").permissions("READ,WRITE").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(2L).username("user").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(3L).username("ram").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(4L).username("anvi").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(5L).username("manager").password("*****").roles("MANAGER").permissions("READ,WRITE,DROP").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(1L).username("admin").password("*****").roles("ADMIN,USER").permissions("READ,WRITE").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(2L).username("user").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(3L).username("ram").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(4L).username("anvi").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(5L).username("manager").password("*****").roles("MANAGER").permissions("READ,WRITE,DROP").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(1L).username("admin").password("*****").roles("ADMIN,USER").permissions("READ,WRITE").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(2L).username("user").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(3L).username("ram").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(4L).username("anvi").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(5L).username("manager").password("*****").roles("MANAGER").permissions("READ,WRITE,DROP").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(1L).username("admin").password("*****").roles("ADMIN,USER").permissions("READ,WRITE").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(2L).username("user").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(3L).username("ram").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(4L).username("anvi").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(5L).username("manager").password("*****").roles("MANAGER").permissions("READ,WRITE,DROP").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(1L).username("admin").password("*****").roles("ADMIN,USER").permissions("READ,WRITE").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(2L).username("user").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(3L).username("ram").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(4L).username("anvi").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(5L).username("manager").password("*****").roles("MANAGER").permissions("READ,WRITE,DROP").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(1L).username("admin").password("*****").roles("ADMIN,USER").permissions("READ,WRITE").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(2L).username("user").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(3L).username("ram").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(4L).username("anvi").password("*****").roles("USER").permissions("READ").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        list.add(User.builder().id(5L).username("manager").password("*****").roles("MANAGER").permissions("READ,WRITE,DROP").active(1).blocked(0).createdDate(new Date()).createdBy("admin").updatedDate(new Date()).updatedBy("admin").build());
        return list;
    }
}
