package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.services.CustomSuccessHandler;
import com.example.demo.services.MyUserDetailsService;

// import com.example.demo.services.MyAdminDetailsService;

// import com.example.demo.services.MyAdminDetailsService;

@Configuration
public class SecurityConfiguration {

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Autowired
	CustomSuccessHandler customSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //    http.authorizeHttpRequests().requestMatchers("/countries").authenticated();
    //    http.authorizeHttpRequests().requestMatchers("/category").permitAll();
    //    http.formLogin();
    //     return http.build();

    return http.authorizeHttpRequests(
        authorize -> authorize
        .requestMatchers("/countries","/category","/product").hasAuthority("ADMIN")
        .requestMatchers("/user","/home").hasAuthority("USER")
        .requestMatchers("/**")
        .permitAll()
    )   .formLogin(Customizer.withDefaults())
        .formLogin(login -> login.loginPage("/login")
        .loginProcessingUrl("/login")
        .successHandler(customSuccessHandler).permitAll()
        // .defaultSuccessUrl("/countries")
        .failureUrl("/login-error")
        )
        .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll())
                
        .build();
    }

    // @Autowired
	// private UserDetailsService userDetailsService;
// provided by Spring Security for encoding passwords using the BCrypt hashing algorithm
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
    

    @Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		
		provider.setPasswordEncoder(bCryptPasswordEncoder()); 
        //When a user authenticates, their provided password needs to be encoded before it can be compared against the encoded password stored in the database. 
		return provider;
	}
    
}