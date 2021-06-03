package com.pvapp.PVApp.Utils.Exporter;


import com.pvapp.PVApp.Entities.PVModule;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporterPVModules implements ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<PVModule> modules;

    public ExcelExporterPVModules(List<PVModule> modules) {
        this.modules = modules;
        workbook = new XSSFWorkbook();
    }

    @Override
    public void writeHeaderLine() {
        sheet = workbook.createSheet("modules");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(8);
        style.setFont(font);

        createCell(row, 0, "Producent", style);
        createCell(row, 1, "Model", style);
        createCell(row, 2, "Typ", style);
        createCell(row, 3, "Moc", style);
        createCell(row, 4, "Prad w warunkach STC", style);
        createCell(row, 5, "Prad MPP", style);
        createCell(row, 6, "Napiecie w warunkach STC", style);
        createCell(row, 7, "Napiecie w warunkach MPP", style);
        createCell(row, 8, "Strata temperaturowa", style);
        createCell(row, 9, "Sprawnosc", style);
        createCell(row, 10, "Cena", style);
    }

    @Override
    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Enum) {
            cell.setCellValue(value.toString());
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    @Override
    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(8);
        style.setFont(font);

        for (PVModule pvModule : modules) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, pvModule.getManufacturer(), style);
            createCell(row, columnCount++, pvModule.getModel(), style);
            createCell(row, columnCount++, pvModule.getType(), style);
            createCell(row, columnCount++, pvModule.getPower(), style);
            createCell(row, columnCount++, pvModule.getMaxCurrentSTC(), style);
            createCell(row, columnCount++, pvModule.getCurrentSTC(), style);
            createCell(row, columnCount++, pvModule.getVoltageSTC(), style);
            createCell(row, columnCount++, pvModule.getVoltageMPP(), style);
            createCell(row, columnCount++, pvModule.getTemperatureLost(), style);
            createCell(row, columnCount++, pvModule.getEfficency(), style);
            createCell(row, columnCount++, pvModule.getPrice(), style);
        }
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
