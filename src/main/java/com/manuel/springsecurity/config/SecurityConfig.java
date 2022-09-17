package com.manuel.springsecurity.config;

import com.manuel.springsecurity.controllers.UserDetailsImpl;
import com.manuel.springsecurity.security.FilterRequest;
import com.manuel.springsecurity.security.JwtAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //UserService userDetailsService;
    @Autowired
    private UserDetailsImpl userDetails;
    @Autowired
    FilterRequest filterRequest;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtAuthProvider jwtAuthProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // registering the details of the user with spring security and passing in the
        // password encryption type to the AuthenticationManagerBuilder
       // auth.userDetailsService(userDetails).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(jwtAuthProvider);
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // configuration for path security
        // authorize every http request -> authorizeRequests()
        // for any request to be accepted, it should be authorized -> anyRequest()
        // for creating session on the client, -> sessionManagement();
        http.csrf().disable()

                .authorizeRequests().antMatchers("/home").permitAll()
                .anyRequest().authenticated().and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

                http.addFilterBefore(filterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        // this is for part of an endpoint i dont want to go through authentication
    //web.ignoring();
    }
}
