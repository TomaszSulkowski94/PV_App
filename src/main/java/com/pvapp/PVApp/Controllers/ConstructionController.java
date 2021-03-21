package com.pvapp.PVApp.Controllers;


import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Services.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return "constructionlist";
    }

    @GetMapping("/createConstruction")
    public String createConstruction(Model model) {
        model.addAttribute("construction", new Construction());
        return "constructionform";
    }

    @PostMapping("/saveConstruction")
    public String saveConstruction(@Valid @ModelAttribute("construction") Construction construction, BindingResult result) {
        if (result.hasErrors()) {
            return "constructionform";
        }
        constructionService.saveConstruction(construction);
        return "redirect:/construction/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteConstruction(@PathVariable("id") int id) {
        constructionService.delete(id);
        return "redirect:/construction/list";
    }

    @GetMapping("/edit/{id}")
    public String updateConstruction(@PathVariable("id") int id, Model model) {
        model.addAttribute("construction", constructionService.getConstruction(id));
        return "updateconstructionform";
    }

    @PostMapping("/edit")
    public String updateConstruction(@Valid @ModelAttribute("construction") Construction construction, BindingResult result) {
        if (result.hasErrors()) {
            return "updateconstructionform";
        }
        constructionService.update(construction);
        return "redirect:/construction/list";
    }

}
