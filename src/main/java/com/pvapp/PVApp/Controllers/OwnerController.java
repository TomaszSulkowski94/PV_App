package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.Owner;
import com.pvapp.PVApp.Services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {


    @Autowired
    OwnerService ownerService;

    @GetMapping("/create")
    public String createOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "Owner/ownerform";
    }

    @PostMapping("/save")
    public String createOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.createOwner(owner);
        return "redirect:/owner/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwner(@PathVariable("id") int id) {
        ownerService.deleteOwner(id);
        return "redirect:/owner/list";
    }

    @GetMapping("/edit/{id}")
    public String updateOwner(Model model, @PathVariable("id") int id) {
        model.addAttribute("owner", ownerService.getOwner(id));
        return "Owner/updateownerform";
    }

    @PostMapping("/edit")
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.updateOwner(owner);
        return "redirect:/owner/list";
    }

    @GetMapping("/list")
    public String printOwners(Model model) {
        List<Owner> ownerList = ownerService.getOwners();
        model.addAttribute("owners", ownerList);
        ownerService.getOwners();
        return "Owner/ownerlist";
    }

}
