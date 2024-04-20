package com.example.demo.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    @Autowired BCryptPasswordEncoder encoder;
    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void saveuser(User user) {
       String epass = encoder.encode(user.getPassword());
       user.setPassword(epass);
       user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
     userRepository.save(user);
    }

    public User findbyusername(String userName) {
     return  userRepository.findByUsername(userName);
    }
    
}
