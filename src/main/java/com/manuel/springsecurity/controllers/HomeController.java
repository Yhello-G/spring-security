package com.manuel.springsecurity.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

private  AuthenticationManager authenticationManager;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        authenticationManager.authenticate(authenticationToken);

        return "Hello World In Spring Boot";
    }
}
