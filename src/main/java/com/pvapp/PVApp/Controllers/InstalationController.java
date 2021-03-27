package com.pvapp.PVApp.Controllers;


import com.pvapp.PVApp.Entities.Instalation;

import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Repositories.DBRepositories.PVModuleDBRepo;
import com.pvapp.PVApp.Services.ConstructionService;
import com.pvapp.PVApp.Services.InstalationService;
import com.pvapp.PVApp.Services.InverterService;
import com.pvapp.PVApp.Services.PVModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
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
        model.addAttribute("modules", moduleService.getAllModules());
        model.addAttribute("constructions", constructionService.getAllConstructions());
        model.addAttribute("inverters", inverterService.getAllInverters());
        return "designform";
    }

    @Autowired
    PVModuleDBRepo pvModuleDBRepo;

    @PostMapping("/save")
    public String designInstalation(@ModelAttribute("instalation") Instalation instalation, HttpSession session,
                                    @RequestParam(value = "moduleId", required = false) Integer moduleId,
                                    @RequestParam(value = "constructionId", required = false) Integer constructionId,
                                    @RequestParam(value = "inverterId", required = false) Integer inverterId) {


        System.out.println("ID i parametry");
        System.out.println("Moduł ID:" + instalation.getPvModule().getId());
        System.out.println("Liczba modułów: " + instalation.getNumberofpvmodule());
        System.out.println("Inverter ID:" + instalation.getInverter().getId());
        System.out.println("Konstrukcja ID :" + instalation.getConstruction().getId());
        System.out.println("Liczba falownikow: " + instalation.getNumberofinverters());

        System.out.println("Obiekty");
        System.out.println("Moduł " + moduleService.getPVModule(moduleId).getModel());
        System.out.println("Inverter " + instalation.getInverter().getManufacturer());
        System.out.println("Konstrukcja " + instalation.getConstruction().getManufacturer());


        instalationService.save(instalation, moduleId, inverterId, constructionId);
        return "redirect:/instalation/list";
    }

    @GetMapping("/designPage")
    public String test() {
        return "homePageDesign";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstalation(@PathVariable("id") int id) {
        instalationService.delete(id);
        return "redirect:/instalation/list";
    }

}
