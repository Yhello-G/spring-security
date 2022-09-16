package com.manuel.springsecurity.config;

import com.manuel.springsecurity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // registering the details of the user with spring security and passing in the
        //  encryption type for the encryption
        auth.userDetailsService(userDetailsService).passwordEncoder(encode());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // authorize every http request -> authorizeRequests()
        // for any request to be accepted, it should be authorized -> anyRequest()
        // for creating session on the client, -> sessionManagement();
        http.authorizeRequests().anyRequest().authenticated().and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        // this is for part of an endpoint i dont want to go through authentication
    web.ignoring();
    }
}
