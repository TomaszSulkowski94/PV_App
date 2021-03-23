package com.pvapp.PVApp.Controllers;


import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Services.ConstructionService;
import com.pvapp.PVApp.Services.InstalationService;
import com.pvapp.PVApp.Services.InverterService;
import com.pvapp.PVApp.Services.PVModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String printInstalationInfo(Model model) {
        List<Instalation> instalationsList = instalationService.getAll();
        model.addAttribute("instalations", instalationsList);
        return "instalationlist";
    }

    @GetMapping("/design")
    public String designInstalation(Model model) {
        model.addAttribute("instalation", new Instalation());
        return "designform";
    }

    @PostMapping("/save")
    public String designInstalation(@ModelAttribute("instalation") Instalation instalation) {
        instalationService.save(instalation);
        return "redirect:/instalationlist";
    }

    @GetMapping("/designPage")
    public String test() {
        return "homePageDesign";
    }

}
