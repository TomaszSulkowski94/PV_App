package com.pvapp.PVApp.Utils.Exporter;

import com.pvapp.PVApp.Entities.Inverter;
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

public class ExcelExporterInverter implements ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Inverter> inverters;

    public ExcelExporterInverter(List<Inverter> inverters) {
        this.inverters = inverters;
        workbook = new XSSFWorkbook();
    }

    @Override
    public void writeHeaderLine() {
        sheet = workbook.createSheet("inverters");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(8);
        style.setFont(font);

        createCell(row, 0, "Producent", style);
        createCell(row, 1, "Model", style);
        createCell(row, 2, "Typ", style);
        createCell(row, 3, "Moc DC", style);
        createCell(row, 4, "Moc AC", style);
        createCell(row, 5, "Liczba MPPT", style);
        createCell(row, 6, "Maksymalny prad zwarcia", style);
        createCell(row, 7, "Maksymalny prad roboczy", style);
        createCell(row, 8, "Dolny zakres napiecia", style);
        createCell(row, 9, "Gorny zakres napiecia", style);
        createCell(row, 10, "Maksymalne napiecie", style);
        createCell(row, 11, "Cena", style);
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

        for (Inverter inverter : inverters) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, inverter.getManufacturer(), style);
            createCell(row, columnCount++, inverter.getModel(), style);
            createCell(row, columnCount++, inverter.getType(), style);
            createCell(row, columnCount++, inverter.getDcpower(), style);
            createCell(row, columnCount++, inverter.getAcpower(), style);
            createCell(row, columnCount++, inverter.getMppt(), style);
            createCell(row, columnCount++, inverter.getMaxcurrentrob(), style);
            createCell(row, columnCount++, inverter.getMaxcurrentrob(), style);
            createCell(row, columnCount++, inverter.getDolnyzakresnapiecia(), style);
            createCell(row, columnCount++, inverter.getGornyzakresnapiecia(), style);
            createCell(row, columnCount++, inverter.getMaksymalnenapiecie(), style);
            createCell(row, columnCount++, inverter.getPrice(), style);
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
