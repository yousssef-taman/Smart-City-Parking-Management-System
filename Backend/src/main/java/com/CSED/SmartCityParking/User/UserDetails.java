package com.CSED.SmartCityParking.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDetails implements UserDetailsService {


    private final UserDAO userDAO;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserDetails(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.CSED.SmartCityParking.User.User user = this.userDAO.getUserByEmail(email);
        return User.builder().username(user.getEmail()).password(user.getPassword()).authorities(user.getRole().toString()).build();
    }

}
