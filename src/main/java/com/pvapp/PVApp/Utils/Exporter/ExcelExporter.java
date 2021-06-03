package com.pvapp.PVApp.Utils.Exporter;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelExporter {

    void writeHeaderLine();

    void createCell(Row row, int columnCount, Object value, CellStyle style);

    void writeDataLines();

    void export(HttpServletResponse response) throws IOException;
}
