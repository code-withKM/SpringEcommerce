package com.example.demo.models;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private User user;


    public UserPrincipal(User user) {
        this.user = user;
        System.out.println(user);
        
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        Collection<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    
        return authorities;

        // return Collections.singleton(new SimpleGrantedAuthority("USER")); 
    }

    @Override
    public String getPassword() {

       return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
    
}
