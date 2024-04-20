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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;



@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryservice;

        @Autowired
    ProductService productService;
    
    @GetMapping("/category")
	public String gohome(Model model) {
        List<Category> category = categoryservice.getcategory();
        model.addAttribute("category",category);
		return "category";
	}

    @PostMapping("/category/addnew")
    public String savecategory(Category category) {
       categoryservice.savecategory(category);
       return "redirect:/category";
      
    }

    
    @RequestMapping("/category/findbyid/{id}")
    @ResponseBody
    public Optional<Category> findbyid(@PathVariable("id") int id) {
        
        return categoryservice.findbyid(id);
    }

    @GetMapping("/category/update")
    public String updatecategory( Category category) {
        categoryservice.savecategory(category);
        return "redirect:/category";
    }
    
    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryservice.delete(id);
        productService.deleteproductbycat(id);
        return "redirect:/category";
    }
    
    
}
