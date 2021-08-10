package com.pvapp.PVApp.Utils.PdfExporter;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pvapp.PVApp.Entities.Inverter;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfExporterInverter {

    private final List<Inverter> inverterList;

    public PdfExporterInverter(List<Inverter> inverterList) {
        this.inverterList = inverterList;
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
        cell.setPhrase(new Phrase("Moc DC", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Moc AC", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Dolny zakres napiecia MPPT", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Gorny zakres napiecia MPPT", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Maksymalne napiecie falownika", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Maksymalny prad roboczy", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Maksymalny prad zwarcia", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Libczba MPPT", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cena", font));
        table.addCell(cell);
    }

    private void writeData(PdfPTable table) {
        Font font = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        for (Inverter inverter : inverterList) {
            cell.setPhrase(new Phrase(String.valueOf(inverter.getId()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getManufacturer()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getModel()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getType()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getDcpower()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getAcpower()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getDolnyzakresnapiecia()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getGornyzakresnapiecia()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getMaksymalnenapiecie()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getMaxcurrentrob()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getMaxcurrentzwarcia()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getMppt()), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(inverter.getPrice()), font));
            table.addCell(cell);
        }
    }

    public void export(ServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
        document.addTitle("Lista falowników");
        document.addAuthor("Tomasz Sulkowski");
        document.addCreationDate();
        document.addSubject("PVApp");

        PdfPTable table = new PdfPTable(new float[]{6,8,8,12,6,6,6,8,8,8,8,8,8});
        table.setWidthPercentage(100f);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String paragraph = "Lista falowników ".concat(currentDateTime);
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
