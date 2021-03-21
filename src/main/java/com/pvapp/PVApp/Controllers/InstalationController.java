package com.pvapp.PVApp.Controllers;


import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Services.InstalationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/instalation")
public class InstalationController {

    @Autowired
    InstalationService instalationService;

    @GetMapping("/list")
    public String printInstalationInfo(Model model) {
        List<Instalation> instalationsList = instalationService.getAll();
        model.addAttribute("instalations", instalationsList);
        return "instalationlist";
    }



    @PostMapping("/save")
    public String saveInstalation() {
        return "";
    }

    public String deleteInstalation() {
        return "";
    }

    public String updateInstalation() {
        return "";
    }
}
