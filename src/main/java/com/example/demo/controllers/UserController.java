package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.User;
import com.example.demo.services.UserService;



@Controller
public class UserController {
    @Autowired UserService userService;
    @GetMapping("/login")
    public String login(){
        return "pages-login";
    }

    @GetMapping("/register")
    public String register() {
        return "pages-register";
    }

    @PostMapping("/users/addnew")
    public String newuser(User user,RedirectAttributes redirectAttributes) {
      User existingUser =  userService.findbyusername(user.getUsername());
      if (existingUser != null) {
        // User already exists, redirect with a message
        redirectAttributes.addFlashAttribute("errorMessage", "User already exists ,Please login");
        return "redirect:/register";
    }
      userService.saveuser(user);
      redirectAttributes.addFlashAttribute("successMessage", "user registered succesfully, pease login to continue");
      return "redirect:/register";
    }
    
    @GetMapping("/login-error")
    public String loginerror(Model model){
     model.addAttribute("errorMessage", "Bad credentials");
     return "pages-login";
    }
    
    
}
