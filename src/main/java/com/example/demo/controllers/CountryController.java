package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Country;
import com.example.demo.services.CountryService;





@Controller
public class CountryController {

    @Autowired
    private CountryService countryservice;
    
    @GetMapping("/countries")
    public String getcountries(Model model) {
        List<Country> countries= countryservice.getcountries();
        model.addAttribute("countries",countries);
        return "country";
    }

    @PostMapping("/countries/addnew")
    public String addcountry(Country country) {
       System.out.println(country);
        countryservice.savecountry(country);
        
        return "redirect:/countries";
    }

    @RequestMapping("/countries/findbyid/{id}")
    @ResponseBody
    public Optional<Country> findbyid(@PathVariable("id") int id) {
        
        return countryservice.findbyid(id);
    }

    @RequestMapping(value = "countries/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET})
    public String update(Country country) {
       System.out.println(country);
        countryservice.savecountry(country);
        
        return "redirect:/countries";
    }

    @RequestMapping("/countries/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        countryservice.deletecountry(id);
        return "redirect:/countries";
    }
    
    
    
    
}
