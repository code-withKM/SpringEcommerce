package com.example.demo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class Pass {
    public static void main(String[] args) {
        String plainPassword = "admin"; // Replace this with the actual plain-text password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(plainPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
