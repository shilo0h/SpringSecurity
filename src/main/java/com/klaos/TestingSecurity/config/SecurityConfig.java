package com.klaos.TestingSecurity.config;

import com.klaos.TestingSecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){    //this is the login page
       return http
                .csrf(customizer->customizer.disable())    //this disables the csrf token
                .authorizeHttpRequests(request-> request.
                        requestMatchers("register","login")
                                .permitAll()
                        .anyRequest().authenticated())
                // http.formLogin(Customizer.withDefaults());
                .httpBasic(Customizer.withDefaults())    //this customizes the login page
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  //this makes so session id changes everytime
               .authenticationProvider(authenticationProvider())
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
               .build();    //this returns the SecurityFiltersChain
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {   //this verifies the database
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
       return config.getAuthenticationManager();

    }

//    @Bean
//    public UserDetailsService userDetailsService(){                          //it tells spring the customize of who will sign in from our side
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("USER")
//                .build();
//
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("admin1")
//                .password("admin1")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
//





    }
