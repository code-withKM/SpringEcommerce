package com.example.demo.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminPrincipal implements UserDetails {

    private Admin admin;


    public AdminPrincipal(Admin admin) {
        this.admin = admin;
       
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("ADMIN")); //ASSIGNING ROLE FOR USER
    }
   

    @Override
    public String getPassword() {

       return admin.getPassword();
    }

    @Override
    public String getUsername() {

        return admin.getUsername();
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
