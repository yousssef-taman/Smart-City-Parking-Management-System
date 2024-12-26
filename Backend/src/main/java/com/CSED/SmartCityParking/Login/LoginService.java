package com.CSED.SmartCityParking.Login;

import com.CSED.SmartCityParking.User.User;
import com.CSED.SmartCityParking.User.UserAndDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public UserAndDriver getUserByUserName (User user) {
        UserAndDriver RetrivedUser = loginRepository.findUserAndDriverByUserName(user.getUsername());
        return RetrivedUser;
        }
    }

