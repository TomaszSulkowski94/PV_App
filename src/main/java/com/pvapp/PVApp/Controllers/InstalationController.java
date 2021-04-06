package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Services.ConstructionService;
import com.pvapp.PVApp.Services.InstalationService;
import com.pvapp.PVApp.Services.InverterService;
import com.pvapp.PVApp.Services.PVModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public String updateInstalation(@ModelAttribute("instalation") Instalation instalation) {
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
    public String designInstalation(@ModelAttribute("instalation") Instalation instalation) {
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


}
