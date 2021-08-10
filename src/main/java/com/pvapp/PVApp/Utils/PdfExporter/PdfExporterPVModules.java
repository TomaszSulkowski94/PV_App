package com.pvapp.PVApp.Utils.PdfExporter;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pvapp.PVApp.Entities.PVModule;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfExporterPVModules {

    private final List<PVModule> moduleList;

    public PdfExporterPVModules(List<PVModule> moduleList) {
        this.moduleList = moduleList;
    }

    private void writeHeader(PdfPTable table) {
        Font font = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producent", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Model", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Typ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Moc", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cena", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Sprawnosc", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Napięcie robocze w warunkach STC", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Napięcie obwodu otwartego w warunkach STC", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prąd zwarcia w warunkach STC", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prąd w punkcie pracy maksymalnej w warunkach STC", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Strata temperaturowa", font));
        table.addCell(cell);
    }

    private void writeData(PdfPTable table) {
        Font font = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        for (PVModule pvModule : moduleList) {
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getId()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getManufacturer()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getModel()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getType()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getPower()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getPrice()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getEfficency()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getVoltageMPP()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getVoltageSTC()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getCurrentSTC()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getMaxCurrentSTC()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(pvModule.getTemperatureLost()), font));
            table.addCell(cell);
        }
    }

    public void export(ServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
        document.addTitle("Lista modułów PV");
        document.addAuthor("Tomasz Sulkowski");
        document.addCreationDate();
        document.addSubject("PVApp");

        PdfPTable table = new PdfPTable(new float[]{6, 8, 6, 14, 6, 6, 6, 8, 8, 8, 8, 8});
        table.setWidthPercentage(100f);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String paragraph = "Lista modułów PV ".concat(currentDateTime);
        Paragraph par = new Paragraph(paragraph, font);
        par.setAlignment(Element.ALIGN_CENTER);
        document.add(par);
        document.add(Chunk.NEWLINE);

        writeHeader(table);
        writeData(table);

        document.add(table);
        document.close();
    }
}