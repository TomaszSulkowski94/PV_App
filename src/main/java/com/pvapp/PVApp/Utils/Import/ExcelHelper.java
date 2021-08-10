package com.pvapp.PVApp.Utils.Import;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
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

    private static String checkInvType(String val) {
        // ENUMS JEDNOFAZOWY, TROJFAZOWY
        String[] jednofaz = {"JEDNOFAZOWY", "JEDNO-FAZOWY", "JEDNO_FAZOWY", "JEDNO FAZOWY"};
        String[] trojfaz = {"TRÓJFAZOWY", "TRÓJ-FAZOWY", "TRÓJ FAZOWY", "TRÓJ_FAZOWY", "TROJFAZOWY", "TROJ-FAZOWY", "TROJ FAZOWY", "TROJ_FAZOWY"};
        if (checkEnum(val, jednofaz)) {
            return "JEDNOFAZOWY";
        } else if (checkEnum(val, trojfaz)) {
            return "TROJFAZOWY";
        } else {
            return " ";
        }
    }

    private static String checkModuleType(String val) {
        // ENUMS POLIKRYSTALICZNY, MONOKRYSTALICZNY, BIFACIAL, GLASSGLASS
        String[] poli = {"POLIKRYSTALICZNY", "POLI", "POLY", "POLIKRYSTALICZNY", "POLI-KRYSTALICZNY", "POLI KRYSTALICZNY", "POLI_KRYSTALICZNY"};
        String[] mono = {"MONOKRYSTALICZNY", "MONO", "MONOKRYSTALICZNY", "MONO-KRYSTALICZNY", "MONO KRYSTALICZNY", "MONO_KRYSTALICZNY"};
        String[] glass = {"GLASSGLASS", "GLASS GLASS", "GLASS-GLASS", "GLASS_GLASS", "GLASS"};
        String[] bifacial = {"BIFACIAL", "BIFACJAL", "BI-FACIAL", "BI FACIAL", "BI_FACIAL"};
        if (checkEnum(val, poli)) {
            return "POLIKRYSTALICZNY";
        } else if (checkEnum(val, mono)) {
            return "MONOKRYSTALICZNY";
        } else if (checkEnum(val, glass)) {
            return "GLASSGLASS";
        } else if (checkEnum(val, bifacial)) {
            return "BIFACIAL";
        } else {
            return " ";
        }
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
                            String typeInv = checkInvType(currentCell.getStringCellValue());
                            Inverter.InverterType type = Inverter.InverterType.valueOf(typeInv);
                            inverter.setType(type);
                            break;
                        case 3:
                            inverter.setDcpower((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            inverter.setAcpower((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            inverter.setMppt((int) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            inverter.setMaxcurrentzwarcia(currentCell.getNumericCellValue());
                            break;
                        case 7:
                            inverter.setMaxcurrentrob(currentCell.getNumericCellValue());
                            break;
                        case 8:
                            inverter.setDolnyzakresnapiecia((int) currentCell.getNumericCellValue());
                            break;
                        case 9:
                            inverter.setGornyzakresnapiecia((int) currentCell.getNumericCellValue());
                            break;
                        case 10:
                            inverter.setMaksymalnenapiecie((int) currentCell.getNumericCellValue());
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
                            String mt = checkModuleType(currentCell.getStringCellValue());
                            PVModule.moduleType moduleType = PVModule.moduleType.valueOf(mt);
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

    private static boolean checkEnum(String val, String[] checkArray) {
        val = val.toUpperCase();
        for (int i = 0; i < checkArray.length; i++) {
            log.info("Value from table " + checkArray[i]);
            if (val.equals(checkArray[i])) {
                log.info("Sucesss for value " + checkArray[i]);
                return true;
            }
        }
        log.info("false");
        return false;
    }


    private static String roofMaterialChecks(String materialType) {
        // Enums BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT, DACHÓWKA_CERAMICZNA, DACHÓWKA_KARPIÓWKA
        String[] allBlachodach = {"BLACHODACHÓWKA", "BLACHODACHOWKA", "BLACHO-DACHÓWKA", "BLACHO-DACHOWKA",
                "BLACHO DACHÓWKA", "BLACHO DACHOWKA"};
        String[] allBlachoTrapez = {"BLACHO TRAPEZ", "BLACHO-TRAPEZ", "BLACHOTRAPEZ", "BLACHO_TRAPEZ"};
        String[] allPlytaWarstwowa = {"PŁYTAWARSTWOWA", "PŁYTA WARSTWOWA", "PŁYTA_WARSTWOWA", "PŁYTA-WARSTWOWA",
                "PLYTAWARSTWOWA", "PLYTA WARSTWOWA", "PLYTA_WARSTWOWA", "PLYTA-WARSTWOWA"};
        String[] allDachCer = {"DACHÓWKACERAMICZNA", "DACHÓWKA CERAMICZNA", "DACHÓWKA_CERAMICZNA",
                "DACHÓWKA-CERAMICZNA", "DACHOWKACERAMICZNA", "DACHOWKA CERAMICZNA", "DACHOWKA-CERAMICZNA", "DACHOWKA_CERAMICZNA"};
        String[] allDachKar = {"DACHÓWKA KARPIÓWKA", "DACHÓWKAKARPIÓWKA", "DACHÓWKA_KARPIÓWKA", "DACHÓWKA-KARPIÓWKA",
                "DACHOWKA KARPIÓWKA", "DACHOWKAKARPIÓWKA", "DACHOWKA_KARPIOWKA", "DACHOWKA-KARPIOWKA", "DACHOWKA KARPIOWKA",
                "DACHOWKAkarpiowka", "DACHOWKA_KARPIOWKA", "DACHOWKA-KARPIOWKA"};

        if (checkEnum(materialType, allBlachodach)) {
            return "BLACHODACHOWKA";
        } else if (checkEnum(materialType, allBlachoTrapez)) {
            return "BLACHOTRAPEZ";
        } else if (checkEnum(materialType, allPlytaWarstwowa)) {
            return "PLYTA_WARSTWOWA";
        } else if (materialType.equals("PAPA")) {
            return "PAPA";
        } else if (materialType.equals("GONT")) {
            return "GONT";
        } else if (materialType.equals("GRUNT")) {
            return "GRUNT";
        } else if (checkEnum(materialType, allDachCer)) {
            return "DACHÓWKA_CERAMICZNA";
        } else if (checkEnum(materialType, allDachKar)) {
            return "DACHÓWKA_KARPIÓWKA";
        } else {
            return " ";
        }
    }

    private static String roofTypeChecks(String constrType) {
        String[] allDachPlaski = {"DACH-PLASKI", "DACH-PLASKI", "DACH PLASKI", "DACH PŁASKI", "DACHPŁASKI", "DACHPLASKI", "DACH_PLASKI", "DACH_PŁASKI"};
        String[] allDachSkosny = {"DACH-SKOŚNY", "DACH-SKOSNY", "DACHSKOŚNY", "DACH SKOŚNY", "DACHSKOSNY", "DACH SKOSNY", "DACH_SKOSNY", "DACH_SKOŚNY"};

        if (checkEnum(constrType, allDachPlaski)) {
            return "DACH_PLASKI";
        } else if (checkEnum(constrType, allDachSkosny)) {
            return "DACH_SKOSNY";
        } else if (constrType.equals("GRUNT")) {
            return "GRUNT";
        } else {
            return "";
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
                            String roofMat = roofMaterialChecks(currentCell.getStringCellValue());
                            Construction.roofMaterial rm = Construction.roofMaterial.valueOf(roofMat);
                            construction.setRoofmaterial(rm);
                            break;
                        case 4:
                            construction.setRoofslope((int) (currentCell.getNumericCellValue()));
                            break;
                        case 5:
                            String constrType = roofTypeChecks(currentCell.getStringCellValue());
                            Construction.roofType rt = Construction.roofType.valueOf(constrType);
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