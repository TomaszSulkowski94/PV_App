package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Services.InverterService;
import com.pvapp.PVApp.Utils.Exporter.ExcelExporterInverter;
import com.pvapp.PVApp.Utils.Import.ExcelHelper;
import com.pvapp.PVApp.Utils.PdfExporter.PdfExporterInverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/inverter")
public class InvertersController {

    @Autowired
    InverterService inverterService;

    @GetMapping("/list")
    public String getInverters(Model model) {
        List<Inverter> inverterList = inverterService.getAllInverters();
        model.addAttribute("inverters", inverterList);
        return "Inverter/inverterlist";
    }

    @GetMapping("/create")
    public String createInverter(Model model) {
        model.addAttribute("inverter", new Inverter());
        return "Inverter/inverterform";
    }

    @PostMapping("/save")
    public String saveInverter(@Valid @ModelAttribute("inverter") Inverter inverter, BindingResult result) {
        if (result.hasErrors()) {
            return "Inverter/inverterform";
        }
        inverterService.saveInverter(inverter);
        return "redirect:/inverter/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteInverter(@PathVariable("id") int id) {
        try {
            inverterService.deleteInverter(id);
            return "redirect:/inverter/list";
        } catch (DataIntegrityViolationException ex) {
            return "Exception/invertererror";
        }
    }

    @GetMapping("/edit/{id}")
    public String editInverter(@PathVariable("id") int id, Model model) {
        model.addAttribute("inverter", inverterService.getInverter(id));
        return "Inverter/updateinverterform";
    }

    @PostMapping("/edit")
    public String editInverter(@Valid @ModelAttribute("inverter") Inverter inverter, BindingResult result) {
        if (result.hasErrors()) {
            return "Inverter/updateinverterform";
        }
        inverterService.update(inverter);
        return "redirect:/inverter/list";
    }

    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=InverterList" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Inverter> inverters = inverterService.getAllInverters();

        PdfExporterInverter exporter = new PdfExporterInverter(inverters);
        exporter.export(response);
    }

    @GetMapping("/upload")
    public String uploadFileForm() {
        return "Inverter/inverterimport";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                inverterService.saveFile(file);
                log.info("Uploaded the file successfully: " + file.getOriginalFilename());
                return "redirect:/inverter/list";
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Could not upload the file: " + file.getOriginalFilename() + "!");
                return "Inverter/inverterimport";
            }
        }
        log.error("Please upload an excel file!");
        return "Inverter/inverterimport";
    }


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=templateInverter" + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Inverter> inverters = inverterService.getAllInverters();

        ExcelExporterInverter excelExporter = new ExcelExporterInverter(inverters);

        excelExporter.export(response);
    }
}
