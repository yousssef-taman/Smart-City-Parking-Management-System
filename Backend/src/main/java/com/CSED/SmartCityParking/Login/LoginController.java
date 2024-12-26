package com.CSED.SmartCityParking.Login;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.CSED.SmartCityParking.User.User;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @PostMapping
    Object validate(@RequestBody User user)
    {
        User temp = loginService.getUserByUserName(user);
        if (temp != null)
        {
            return encoder.matches(user.getPassword(),temp.getPassword()) ? temp: "Wrong Password" ;
        }else {
            return "UserName is not found";
        }
    }
}
