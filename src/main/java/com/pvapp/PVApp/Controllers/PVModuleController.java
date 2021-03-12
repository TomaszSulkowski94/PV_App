package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Domain.PVModule;
import com.pvapp.PVApp.Services.PVModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PVModuleController {

    @Autowired
    PVModuleService pvModuleService;

    @GetMapping("/modulelist")
    public String getModules(Model model) {
        List<PVModule> allPVModules = pvModuleService.getAllModules();
        model.addAttribute("modules", allPVModules);
        return "modulelist";
    }

    @GetMapping("/createModule")
    public String newModule(Model model) {
        model.addAttribute("pvModule", new PVModule());
        return "pvform";
    }

    @PostMapping("/PVModules")
    public String saveModule(PVModule pvmodule) {
        pvModuleService.saveModule(pvmodule);
        return "redirect:/modulelist";
    }


    @RequestMapping("/modulelist/{id}")
    public String deleteModule(@PathVariable("id") int id) {
        pvModuleService.deleteModule(id);
        return "redirect:/modulelist";
    }


}
