package com.manuel.springsecurity.service;

import com.manuel.springsecurity.UserHelper;
import com.manuel.springsecurity.UserPojo;
import com.manuel.springsecurity.repository.UserDAO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

   private UserHelper userHelper;
    private UserDAO userDAO;
    @Override
    public UserHelper loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPojo userPojo = null;
        try {
            userPojo = userDAO.getUserDetails(username);
            UserHelper userHelper = new UserHelper(userPojo);
            return userHelper;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new UsernameNotFoundException("User" + username +" was not found");

        }

    }
}
