package com.manuel.springsecurity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        Map<String,String> user = new HashMap<String, String>();
        user.put("Emmanuel",  ("password"));
        if (user.containsKey(username)){
           userDetails = new User(username, passwordEncoder.encode( user.get(username)), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException(username);
        }
        // now pass this custom userDetails to authentication manager
        return userDetails;
    }
}
