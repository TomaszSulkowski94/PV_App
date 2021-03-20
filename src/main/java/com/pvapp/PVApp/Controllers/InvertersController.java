package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Domain.Inverter;
import com.pvapp.PVApp.Services.InverterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/inverter")
public class InvertersController {

    @Autowired
    InverterService inverterService;

    @GetMapping("/list")
    public String getInverters(Model model) {
        List<Inverter> inverterList = inverterService.getAllInverters();
        model.addAttribute("inverters", inverterList);
        return "inverterlist";
    }

    @GetMapping("/create")
    public String createInverter(Model model) {
        model.addAttribute("inverter", new Inverter());
        return "inverterform";
    }

    @PostMapping("/save")
    public String saveInverter(@Valid @ModelAttribute("inverter") Inverter inverter, BindingResult result) {
        if(result.hasErrors()){
            return "inverterform";
        }
        inverterService.saveInverter(inverter);
        return "redirect:/inverter/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteInverter(@PathVariable("id") int id) {
        inverterService.deleteInverter(id);
        return "redirect:/inverter/list";
    }

    @GetMapping("/edit/{id}")
    public String editInverter(@PathVariable("id") int id, Model model) {
        model.addAttribute("inverter", inverterService.getInverter(id));
        return "updateinverterform";
    }

    @PostMapping("/edit")
    public String editInverter(@ModelAttribute("inverter") Inverter inverter) {
        inverterService.update(inverter);
        return "redirect:/inverter/list";
    }

}
