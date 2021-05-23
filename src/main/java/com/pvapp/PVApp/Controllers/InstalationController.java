package com.pvapp.PVApp.Controllers;


import com.pvapp.PVApp.Utils.PdfExporterInstalation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Services.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/instalation")
public class InstalationController {

    @Autowired
    InstalationService instalationService;

    @Autowired
    PVModuleService moduleService;

    @Autowired
    InverterService inverterService;

    @Autowired
    ConstructionService constructionService;


    @GetMapping("/report/{id}")
    public String viewReport(Model model, @PathVariable("id") int id) {
        model.addAttribute("instalation", instalationService.getById(id));
        return "Report/report";
    }

    @GetMapping("/list")
    public String printInstalations(Model model) {
        List<Instalation> instalationsList = instalationService.getAll();
        model.addAttribute("instalations", instalationsList);
        return "Instalation/instalationlist";
    }

    @GetMapping("/edit/{id}")
    public String updateInstalation(Model model, @PathVariable("id") int id) {
        model.addAttribute("instalation", instalationService.getById(id));
        model.addAttribute("modules", moduleService.getAllModules());
        model.addAttribute("constructions", constructionService.getAllConstructions());
        model.addAttribute("inverters", inverterService.getAllInverters());
        return "Instalation/updateinstalation";
    }

    @PostMapping("/edit")
    public String updateInstalation(@Valid @ModelAttribute("instalation") Instalation instalation, BindingResult result) {
        if (result.hasErrors()) {
            return "Instalation/updateinstalation";
        }
        instalationService.update(instalation);
        return "redirect:/instalation/list";

    }

    @GetMapping("/design")
    public String designInstalation(Model model) {
        model.addAttribute("instalation", new Instalation());
        model.addAttribute("modules", moduleService.getAllModules());
        model.addAttribute("constructions", constructionService.getAllConstructions());
        model.addAttribute("inverters", inverterService.getAllInverters());
        return "Instalation/designform";
    }


    @PostMapping("/save")
    public String designInstalation(@Valid @ModelAttribute("instalation") Instalation instalation, BindingResult result) {
        if (result.hasErrors()) {
            return "Instalation/designform";
        }
        instalationService.save(instalation);
        return "redirect:/instalation/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstalation(@PathVariable("id") int id) {
        instalationService.delete(id);
        return "redirect:/instalation/list";
    }


    @GetMapping("/designPage")
    public String test() {
        return "homePageDesign";
    }


    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=InstalationList_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Instalation> instalations = instalationService.getAll();

        PdfExporterInstalation exporter = new PdfExporterInstalation(instalations);
        exporter.exportInstalationList(response);
    }
}
