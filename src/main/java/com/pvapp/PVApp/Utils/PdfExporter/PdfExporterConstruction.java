package com.pvapp.PVApp.Utils.PdfExporter;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pvapp.PVApp.Entities.Construction;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfExporterConstruction {

    private List<Construction> constructionList;

    public PdfExporterConstruction(List<Construction> constructionList) {
        this.constructionList = constructionList;
    }

    private void writeHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Font font = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producent", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Model", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Typ dachu", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Material wykonania dachu", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Kat nachylenia instalacji", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cena", font));
        table.addCell(cell);
    }

    private void writeDataRows(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Font font = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        for (Construction construction : constructionList) {
            cell.setPhrase(new Phrase(String.valueOf(construction.getId()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(construction.getManufacturer()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(construction.getModel()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(construction.getRooftype()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(construction.getRoofmaterial()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(construction.getRoofslope()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(construction.getPrice()), font));
            table.addCell(cell);
        }
    }

    public void exportConstructionList(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
        document.addTitle("Lista konstrukcji montazowych");
        document.addAuthor("Tomasz Sulkowski");
        document.addCreationDate();
        document.addSubject("PVApp");

        PdfPTable table = new PdfPTable(new float[]{5, 15, 10, 25, 25, 15, 10});
        table.setWidthPercentage(90f);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String paragraph = "Lista konstrukcji ".concat(currentDateTime);
        Paragraph par = new Paragraph(paragraph, font);
        par.setAlignment(Element.ALIGN_CENTER);
        document.add(par);
        document.add(Chunk.NEWLINE);

        writeHeader(table);
        writeDataRows(table);

        document.add(table);
        document.close();
    }
}

