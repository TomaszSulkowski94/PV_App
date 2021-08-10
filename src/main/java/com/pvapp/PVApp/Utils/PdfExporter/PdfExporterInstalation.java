package com.pvapp.PVApp.Utils.PdfExporter;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.pvapp.PVApp.Entities.Instalation;


public class PdfExporterInstalation {

    private final List<Instalation> instalationList;


    public PdfExporterInstalation(List<Instalation> instalationList) {
        this.instalationList = instalationList;
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Font font = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Moc instalacji", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Liczba modulow", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producent modulow PV", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Model modulu", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Moc", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Typ modulu", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producent falownika", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Model falownika", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Typ falownika", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producent konstrukcji", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Model konstrukcji", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Roczna produkcja energii elektrycznej", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cena netto instalacji", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cena brutto instalacji", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        Font font = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        for (Instalation instalation : instalationList) {
            cell.setPhrase(new Phrase(String.valueOf(instalation.getId()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPower()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getNumberofpvmodule()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPvModule().getManufacturer()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPvModule().getModel()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPvModule().getPower()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPvModule().getType()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getInverter().getManufacturer()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getInverter().getModel()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getInverter().getType()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getConstruction().getManufacturer()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getConstruction().getModel()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getProduction().getSummary()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPrice().getInstalationpricenet()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(instalation.getPrice().getInstalationpricegross()), font));
            table.addCell(cell);
        }
    }


    public void exportInstalationList(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
        document.addTitle("Lista instalacji");
        document.addAuthor("Tomasz Sulkowski");
        document.addCreationDate();
        document.addSubject("PVApp");

        PdfPTable table = new PdfPTable(new float[]{3, 7, 4, 7, 5, 4, 13, 6, 10, 9, 7, 7, 6, 6, 6});
        table.setWidthPercentage(100f);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);



        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String paragraph = "Lista instalacji ".concat(currentDateTime);
        Paragraph par = new Paragraph(paragraph, font);
        par.setAlignment(Element.ALIGN_CENTER);
        document.add(par);
        document.add(Chunk.NEWLINE);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }


}
