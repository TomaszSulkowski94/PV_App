package com.pvapp.PVApp.Utils.Import;

import com.pvapp.PVApp.Entities.Construction;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    private final static String TYPE = "text/csv";
    private final static String[] ConstructionHeaders = {"Producent", "Model", "Typ dachu", "Material wykonania dachu", "Cena", "Kat nachylenia"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Construction> csvConstructions(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Construction> constructions = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Construction construction = new Construction(
                        csvRecord.get("Producent"),
                        csvRecord.get("Model"),
                        Construction.roofType.valueOf(csvRecord.get("Typ dachu")),
                        Construction.roofMaterial.valueOf(csvRecord.get("Material wykonania dachu")),
                        Double.parseDouble(csvRecord.get("Cena")),
                        Integer.parseInt(csvRecord.get("Kat nachylenia")));
                constructions.add(construction);
            }
            return constructions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<Construction> constructions) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Construction construction : constructions) {
                List<String> data = Arrays.asList(
                        construction.getManufacturer(),
                        construction.getModel(),
                        String.valueOf(construction.getRooftype()),
                        String.valueOf(construction.getRoofmaterial()),
                        String.valueOf(construction.getPrice()),
                        String.valueOf(construction.getRoofslope()));
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}




