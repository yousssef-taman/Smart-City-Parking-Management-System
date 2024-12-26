package com.CSED.SmartCityParking.Login;

import com.CSED.SmartCityParking.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public User getUserByUserName (User user) {
        User RetrivedUser = loginRepository.findUserByUserName(user.getUsername());
        return RetrivedUser;
        }
    }

