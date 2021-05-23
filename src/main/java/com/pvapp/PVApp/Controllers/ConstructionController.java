package com.pvapp.PVApp.Controllers;


import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Services.ConstructionService;
import com.pvapp.PVApp.Utils.PdfExporterConstruction;
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
@RequestMapping("/construction")
public class ConstructionController {

    @Autowired
    ConstructionService constructionService;

    @GetMapping("/list")
    public String getconstructions(Model model) {
        List<Construction> constructionList = constructionService.getAllConstructions();
        model.addAttribute("constructions", constructionList);
        return "Construction/constructionlist";
    }

    @GetMapping("/createConstruction")
    public String createConstruction(Model model) {
        model.addAttribute("construction", new Construction());
        return "Construction/constructionform";
    }

    @PostMapping("/saveConstruction")
    public String saveConstruction(@Valid @ModelAttribute("construction") Construction construction, BindingResult result) {
        if (result.hasErrors()) {
            return "Construction/constructionform";
        }
        constructionService.saveConstruction(construction);
        return "redirect:/construction/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteConstruction(@PathVariable("id") int id) {
        try {
            constructionService.delete(id);
            return "redirect:/construction/list";
        } catch (DataIntegrityViolationException ex) {
            return "Exception/constructionerror";
        }
    }

    @GetMapping("/edit/{id}")
    public String updateConstruction(@PathVariable("id") int id, Model model) {
        model.addAttribute("construction", constructionService.getConstruction(id));
        return "Construction/updateconstructionform";
    }

    @PostMapping("/edit")
    public String updateConstruction(@Valid @ModelAttribute("construction") Construction construction, BindingResult result) {
        if (result.hasErrors()) {
            return "Construction/updateconstructionform";
        }
        constructionService.update(construction);
        return "redirect:/construction/list";
    }

    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ConstructionsList" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Construction> constructions = constructionService.getAllIdOrder();
        PdfExporterConstruction exporter = new PdfExporterConstruction(constructions);
        exporter.exportConstructionList(response);
    }
}
