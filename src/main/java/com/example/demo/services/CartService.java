package com.example.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.models.ShoppingCart;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
@Service

public class CartService {
    @Autowired CartRepository cartRepository;

    @Autowired ProductRepository productRepository;

    @Autowired UserRepository userRepository;


    @Autowired UserService userService;

    public void addItemToCart(int pid, String userName, int quantity,double price) {
        Integer addedQuantity = quantity;
        Product product = productRepository.getbyid(pid);
        User user = userRepository.findByUsername(userName);
        int user_id =user.getId();
       
        ShoppingCart cart = cartRepository.findByUserAndProduct(user_id, pid);
        System.out.println("news" + cart);

        if (cart != null) {
            addedQuantity = cart.getTotalItems() + quantity;
            cart.setTotalItems(addedQuantity);
            int items = cart.getTotalItems();
            System.out.println(items + "items");
            cart.setTotalPrice(items * price);
            System.out.println("if");
        }
        //The product hasn't been added to the shopping card by this user
        else {
            cart = new ShoppingCart();
            cart.setTotalItems(quantity);
            cart.setUser(user);
            cart.setProduct(product);
            cart.setTotalPrice(price);
            System.out.println("else");
            
        }
        cartRepository.save(cart);

       
    }

    public java.util.Optional<ShoppingCart> getItemById(int pid) {
       return cartRepository.findById(pid);
    }

    public int getuserid(String userName) {
      User user = userRepository.findByUsername(userName);
      int user_id =user.getId();
      return user_id;
    }

    public List<ShoppingCart> getproductsbyid(int user_id) {
       return cartRepository.findByuserId(user_id);
    }

    public void remove(int id) {
       cartRepository.deleteById(id);
    }

}
