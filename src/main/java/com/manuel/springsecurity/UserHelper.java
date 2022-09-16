package com.manuel.springsecurity;

import org.springframework.security.core.userdetails.User;

public class UserHelper extends User {

    private static final long serialVersionUID = 1L;

    public UserHelper(UserPojo user) {
        super(user.getUsername(), user.getPassword(), user.getListOfGrantedAuthorities());
    }


    }



