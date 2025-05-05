package com.CSED.SmartCityParking.User;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserDTO> {


    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getLicence());
    }
    
}
