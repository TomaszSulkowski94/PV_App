package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Services.PVModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return "modulelist";
    }

    @GetMapping("/createModule")
    public String newModule(Model model) {
        model.addAttribute("pvModule", new PVModule());
        return "pvform";
    }

    @PostMapping("/saveModule")
    public String saveModule(@Valid @ModelAttribute("pvModule") PVModule pvmodule, BindingResult result) {
        if (result.hasErrors()) {
            return "pvform";
        }
        pvModuleService.saveModule(pvmodule);
        return "redirect:/modules/modulelist";
    }


    @GetMapping("/modulelist/{id}")
    public String deleteModule(@PathVariable("id") int id) {
        pvModuleService.deleteModule(id);
        return "redirect:/modules/modulelist";
    }

    @GetMapping("/modulelist/edit/{id}")
    public String updateModule(@PathVariable("id") int id, Model model) {
        model.addAttribute("pvModule", pvModuleService.getPVModule(id));
        return "updatepvmodule";
    }

    @PostMapping("/modulelist/edit")
    public String updateModule(@Valid @ModelAttribute("pvModule") PVModule pvModule, BindingResult result) {
        if (result.hasErrors()) {
            return "updatepvmodule";
        }
        pvModuleService.updatemodule(pvModule);
        return "redirect:/modules/modulelist";
    }
}
