package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.models.Admin;
import com.example.demo.models.AdminPrincipal;
import com.example.demo.repositories.AdminRepository;
@Service
public class MyAdminDetailsService implements UserDetailsService {
@Autowired AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Admin admin=adminRepository.findByUsername(username);
       System.out.println(admin);

       if(admin == null){
        throw new UsernameNotFoundException(" username not found!");
       }
       return new AdminPrincipal(admin);
    }
    
}
