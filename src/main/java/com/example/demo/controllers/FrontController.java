package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;
import com.example.demo.models.ShoppingCart;
import com.example.demo.services.CartService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

@Controller
public class FrontController {

    @Autowired UserService userService;

    @Autowired UserDetailsService userDetailsService;

    @Autowired ProductService productService;

    @Autowired CartService cartService;

    @GetMapping("/home")
    public String home(Model model,Principal principal){  
        UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
        List <Product> product = productService.getallproduct();
        System.out.println(product);
        System.out.println("new" + userdetails );
        if (userdetails == null) {
            return "redirect:/";
        }
        model.addAttribute("user", userdetails);
        model.addAttribute("products", product);

        return "index";
    }

    @GetMapping("/")
    
    public String index(Model model){
        List <Product> product = productService.getallproduct();
        model.addAttribute("products", product);
        return "index";
    }

    @GetMapping("/user")
    public String user(){
        return "userpage";
    }

    @GetMapping("/cart")
    public String cart(Model model,Principal principal){

        UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
        String userName = principal.getName();
        int user_id = cartService.getuserid(userName);
        System.out.println(user_id + "kku");
        List<ShoppingCart> carts =cartService.getproductsbyid(user_id);
        System.out.println(carts + "kk");
        // System.out.println(userdetails + "mm");
        double totalPrice = 0.0;
        for (ShoppingCart cart : carts) {
            totalPrice += cart.getTotalPrice();
        }
        model.addAttribute("user", userdetails);
        model.addAttribute("cart", carts);
        model.addAttribute("totalPrice", totalPrice);




        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addtocart(@RequestParam int pid,@RequestParam(value="quantity",required = false,defaultValue = "1") int quantity,Principal principal,
                             @RequestParam double price){
        
        
        String userName = principal.getName();
        System.out.println(price +"priced");
        cartService.addItemToCart(pid,userName,quantity,price);
        
        return "redirect:/cart";
        
    }

    @GetMapping("/remove/{id}")
    public String removecart(@PathVariable("id") int id){
        cartService.remove(id);
        return "redirect:/cart";
    }
}
