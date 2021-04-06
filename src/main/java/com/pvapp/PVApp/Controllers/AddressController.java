package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.Address;
import com.pvapp.PVApp.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/create")
    public String createAddress(Model model) {
        model.addAttribute("address", new Address());
        return "Address/addressform";
    }

    @PostMapping("/save")
    public String createAddress(@ModelAttribute("address") Address address) {
        addressService.createAddress(address);
        return "redirect:/address/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") int id) {
        addressService.deleteAddress(id);
        return "redirect:/address/list";
    }

    @GetMapping("/edit/{id}")
    public String updateAddress(Model model, @PathVariable("id") int id) {
        model.addAttribute("address", addressService.getAddress(id));
        return "Address/updateaddressform";
    }

    @PostMapping("/edit")
    public String updateAddress(@ModelAttribute("address") Address address) {
        addressService.updateAddress(address);
        return "redirect:/address/list";
    }

    @GetMapping("/list")
    public String printAdresses(Model model) {
        List<Address> addressList = addressService.getAddresses();
        model.addAttribute("addresses", addressList);
        addressService.getAddresses();
        return "Address/addresslist";
    }


}
