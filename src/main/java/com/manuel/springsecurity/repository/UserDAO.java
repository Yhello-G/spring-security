/*
package com.manuel.springsecurity.repository;


import com.manuel.springsecurity.UserPojo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Repository
public class UserDAO {

    JdbcTemplate jdbcTemplate;
    public UserDAO() {
    }

    public UserPojo getUserDetails(String username) {
        Collection<GrantedAuthority> listOfGrantedAuthorities = new ArrayList<>();
        String userQuery  = "SELECT * FROM users WHERE username = ?";
       List<UserPojo> list = jdbcTemplate.query(userQuery, new String[]{username},(ResultSet rs, int rowNum) -> {
            UserPojo user = new UserPojo();
            user.setUsername(username);
            user.setPassword(rs.getString("PASSWORD"));

            return user;
        });
        if (list.size() > 0){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            listOfGrantedAuthorities.add(grantedAuthority);
            list.get(0).setListOfGrantedAuthorities(listOfGrantedAuthorities);
            return list.get(0);
        }

        return null;
    }




}
*/
