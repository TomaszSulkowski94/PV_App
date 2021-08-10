package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.Price;
import com.pvapp.PVApp.Services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/price")
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        priceService.deletePrice(id);
        return "redirect:/price/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Price> prices = priceService.getAllPrices();
        model.addAttribute("prices", prices);
        return "Price/viewPrices";
    }

    @GetMapping("/setdiscount/{id}")
    public String setDiscount(@PathVariable("id") int id, Model model) {
        model.addAttribute("price", priceService.getPrice(id));
        return "Price/setdiscount";
    }

    @PostMapping("/setdiscount")
    public String setDiscount(@Valid @ModelAttribute("price") Price price, BindingResult result) {
        if (result.hasErrors()) {
            return "Price/setdiscount";
        }
        priceService.setDiscount(price);
        return "redirect:/instalation/list";

    }
}
