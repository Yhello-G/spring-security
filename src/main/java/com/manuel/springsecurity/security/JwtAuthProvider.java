package com.manuel.springsecurity.security;

import com.manuel.springsecurity.controllers.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.config.AuthConfigProvider;
import java.util.ArrayList;
@Service
public class JwtAuthProvider implements AuthenticationProvider {
    @Autowired
    UserDetailsImpl userDetails;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       // Extracting the logged in user from the authentication
        String name = String.valueOf( authentication.getPrincipal());
       String password =String.valueOf(authentication.getCredentials());

       // using the name to get the user from database
       UserDetails user  = userDetails.loadUserByUsername(name);
       if (user != null) {
           if (  passwordEncoder.matches(password, user.getPassword())){
           UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
        return authenticationToken;
       }
       new BadCredentialsException("Error");
       }
      return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
