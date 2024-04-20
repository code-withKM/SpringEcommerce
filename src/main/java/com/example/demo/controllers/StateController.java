package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

import jakarta.validation.Valid;


@Controller
public class StateController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/product")
    public String getallproducts(Model model){
        List<Product> products= productService.getallproduct();
        System.out.println(products); // Optional: printing out the products for debugging
        List<Category> category= categoryService.getcategory();
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping("/product/addnew")
    public String saveproduct(@ModelAttribute("product") @Valid Product product,RedirectAttributes attributes,BindingResult result, @RequestParam("pimage")MultipartFile pimage) {
        if (result.hasErrors()) return "product";
        try {
            productService.saveproduct(pimage,product);
            attributes.addFlashAttribute("success", "Product added successfully!");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "A product with the same name already exists!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add product!");
        }
     
            // productService.saveproduct(product);
            return "redirect:/product";
        
    }
    @RequestMapping("product/editproduct/{id}")
    @ResponseBody
    public Optional<Product> findproductbyid(@PathVariable("id") int id){
       return  productService.findproductbyid(id);
    }

    @PostMapping("product/update")
    public String updateproduct(@ModelAttribute("product") Product product, @RequestParam("pimage") MultipartFile pimage){
        productService.saveproductbyitems(pimage,product);
        return "redirect:/product";
    }

    @GetMapping("product/delete")
    public String deleteproduct(int id){
        System.out.println(id);
        productService.deleteproduct(id);
        return "redirect:/product";
    }
    
}
