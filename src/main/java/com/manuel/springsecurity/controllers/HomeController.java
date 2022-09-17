package com.manuel.springsecurity.controllers;

import com.manuel.springsecurity.security.JwTokenClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HomeController {
    @Autowired
    AuthenticationManager authenticationMan; // new AuthenticationManager();
    @Autowired
    private JwTokenClass jwTokenClass;
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ResponseEntity<String> homePage(@RequestBody LoginDTO loginDTO){

        System.out.println("Email: "+ loginDTO.getPassword());

        try {
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(loginDTO.getName(),
                    loginDTO.getPassword());

            System.out.println(authenticationToken instanceof Authentication);
            authenticationMan.authenticate(authenticationToken);

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

       String jwtToken = jwTokenClass.generate(loginDTO.getName());
       return ResponseEntity.ok(jwtToken);
        //return ResponseEntity.ok("");
    }
}

