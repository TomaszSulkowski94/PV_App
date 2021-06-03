package com.pvapp.PVApp.Utils.Exporter;

import com.pvapp.PVApp.Entities.Construction;
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

public class ExcelExporterConstruction implements ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Construction> constructions;

    public ExcelExporterConstruction(List<Construction> constructions) {
        this.constructions = constructions;
        workbook = new XSSFWorkbook();
    }


    @Override
    public void writeHeaderLine() {
        sheet = workbook.createSheet("constructions");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(8);
        style.setFont(font);

        createCell(row, 0, "Producent", style);
        createCell(row, 1, "Model", style);
        createCell(row, 2, "Cena", style);
        createCell(row, 3, "Materiał wykonania dachu", style);
        createCell(row, 4, "Kąt nachylenia konstrukcji", style);
        createCell(row, 5, "Typ dachu", style);
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

        for (Construction construction : constructions) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, construction.getManufacturer(), style);
            createCell(row, columnCount++, construction.getModel(), style);
            createCell(row, columnCount++, construction.getPrice(), style);
            createCell(row, columnCount++, construction.getRoofmaterial(), style);
            createCell(row, columnCount++, construction.getRoofslope(), style);
            createCell(row, columnCount++, construction.getRooftype(), style);
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
