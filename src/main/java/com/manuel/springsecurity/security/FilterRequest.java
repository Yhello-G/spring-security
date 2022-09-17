package com.manuel.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class FilterRequest extends OncePerRequestFilter {
    @Autowired
    JwTokenClass jwTokenClass;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String header =request.getHeader("Authorization");
         // check the header validity
        if (header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.split(" ")[1].trim();

        // check for token validity
        if (!jwTokenClass.validate(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwTokenClass.getUsername(token);
        UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        authenticatedUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        filterChain.doFilter(request, response);
        System.out.println(header);
    }
}
