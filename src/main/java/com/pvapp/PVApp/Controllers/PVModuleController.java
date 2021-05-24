package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Services.PVModuleService;
import com.pvapp.PVApp.Utils.PdfExporterInverter;
import com.pvapp.PVApp.Utils.PdfExporterPVModules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/modules")
public class PVModuleController {

    @Autowired
    PVModuleService pvModuleService;

    @GetMapping("/modulelist")
    public String getModules(Model model) {
        List<PVModule> allPVModules = pvModuleService.getAllModules();
        model.addAttribute("modules", allPVModules);
        return "Pvmodule/modulelist";
    }

    @GetMapping("/createModule")
    public String newModule(Model model) {
        model.addAttribute("pvModule", new PVModule());
        return "Pvmodule/pvform";
    }

    @PostMapping("/saveModule")
    public String saveModule(@Valid @ModelAttribute("pvModule") PVModule pvmodule, BindingResult result) {
        if (result.hasErrors()) {
            return "Pvmodule/pvform";
        }

        pvModuleService.saveModule(pvmodule);
        return "redirect:/modules/modulelist";
    }


    @GetMapping("/modulelist/{id}")
    public String deleteModule(@PathVariable("id") int id) {
        try {
            pvModuleService.deleteModule(id);
            return "redirect:/modules/modulelist";
        } catch (DataIntegrityViolationException ex) {
            return "Exception/pverror";
        }
    }

    @GetMapping("/error")
    public String moduleException() {
        return "Exception/pverror";
    }

    @GetMapping("/modulelist/edit/{id}")
    public String updateModule(@PathVariable("id") int id, Model model) {
        model.addAttribute("pvModule", pvModuleService.getPVModule(id));
        return "Pvmodule/updatepvmodule";
    }

    @PostMapping("/modulelist/edit")
    public String updateModule(@Valid @ModelAttribute("pvModule") PVModule pvModule, BindingResult result) {
        if (result.hasErrors()) {
            return "Pvmodule/updatepvmodule";
        }
        pvModuleService.updatemodule(pvModule);
        return "redirect:/modules/modulelist";
    }

    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ModulesList" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<PVModule> modules = pvModuleService.getAllModules();

        PdfExporterPVModules exporter = new PdfExporterPVModules(modules);
        exporter.export(response);
    }
}
