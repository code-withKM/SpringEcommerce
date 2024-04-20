package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.models.User;
import com.example.demo.models.UserPrincipal;
import com.example.demo.repositories.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {

   
@Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // BCryptPasswordEncoder.encode(username);
      
       User user=userRepository.findByUsername(username);
       System.out.println(user);

       if(user == null){
        throw new UsernameNotFoundException(" username not found!");
       }
       return new UserPrincipal(user);
    }
    
}
