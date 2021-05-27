package com.pvapp.PVApp.Utils.Import;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelHelper {
    private static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String SHEETCONSTRUCTIONS = "constructions";
    private static final String SHEETPVMODULES = "modules";
    private static final String SHEETIVERTERS = "inverters";


    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Inverter> excelToInverter(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEETIVERTERS);
            Iterator<Row> rows = sheet.iterator();
            List<Inverter> inverters = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Inverter inverter = new Inverter();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            inverter.setManufacturer(currentCell.getStringCellValue());
                            break;
                        case 1:
                            inverter.setModel(currentCell.getStringCellValue());
                            break;
                        case 2:
                            Inverter.InverterType type = Inverter.InverterType.valueOf(currentCell.getStringCellValue());
                            inverter.setType(type);
                            break;
                        case 3:
                            inverter.setDcpower((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            inverter.setAcpower((int)currentCell.getNumericCellValue());
                            break;
                        case 5:
                            inverter.setMppt((int)currentCell.getNumericCellValue());
                            break;
                        case 6:
                            inverter.setMaxcurrentzwarcia(currentCell.getNumericCellValue());
                            break;
                        case 7:
                            inverter.setMaxcurrentrob(currentCell.getNumericCellValue());
                            break;
                        case 8:
                            inverter.setDolnyzakresnapiecia((int)currentCell.getNumericCellValue());
                            break;
                        case 9:
                            inverter.setGornyzakresnapiecia((int)currentCell.getNumericCellValue());
                            break;
                        case 10:
                            inverter.setMaksymalnenapiecie((int)currentCell.getNumericCellValue());
                            break;
                        case 11:
                            inverter.setPrice(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                inverters.add(inverter);
            }
            workbook.close();
            return inverters;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<PVModule> excelToModules(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEETPVMODULES);
            Iterator<Row> rows = sheet.iterator();
            List<PVModule> modules = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                PVModule pvModule = new PVModule();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            pvModule.setManufacturer(currentCell.getStringCellValue());
                            break;
                        case 1:
                            pvModule.setModel(currentCell.getStringCellValue());
                            break;
                        case 2:
                            PVModule.moduleType moduleType = PVModule.moduleType.valueOf(currentCell.getStringCellValue());
                            pvModule.setType(moduleType);
                            break;
                        case 3:
                            pvModule.setPower((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            pvModule.setCurrentSTC(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            pvModule.setMaxCurrentSTC(currentCell.getNumericCellValue());
                            break;
                        case 6:
                            pvModule.setVoltageSTC(currentCell.getNumericCellValue());
                            break;
                        case 7:
                            pvModule.setVoltageMPP(currentCell.getNumericCellValue());
                            break;
                        case 8:
                            pvModule.setTemperatureLost(currentCell.getNumericCellValue());
                            break;
                        case 9:
                            pvModule.setEfficency(currentCell.getNumericCellValue());
                            break;
                        case 10:
                            pvModule.setPrice(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                modules.add(pvModule);
            }
            workbook.close();
            return modules;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Construction> excelToConstruction(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEETCONSTRUCTIONS);
            Iterator<Row> rows = sheet.iterator();
            List<Construction> constructions = new ArrayList<Construction>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Construction construction = new Construction();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            construction.setManufacturer(currentCell.getStringCellValue());
                            break;
                        case 1:
                            construction.setModel(currentCell.getStringCellValue());
                            break;
                        case 2:
                            construction.setPrice(currentCell.getNumericCellValue());
                            break;
                        case 3:
                            Construction.roofMaterial rm = Construction.roofMaterial.valueOf(currentCell.getStringCellValue());
                            construction.setRoofmaterial(rm);
                            break;
                        case 4:
                            construction.setRoofslope((int) (currentCell.getNumericCellValue()));
                            break;
                        case 5:
                            Construction.roofType rt = Construction.roofType.valueOf(currentCell.getStringCellValue());
                            construction.setRooftype(rt);
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                constructions.add(construction);
            }
            workbook.close();
            return constructions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}