package com.CSED.SmartCityParking.Login;
import com.CSED.SmartCityParking.User.UserAndDriver;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<?> validate(@RequestBody User user)
    {
        UserAndDriver temp = loginService.getUserByUserName(user);
        if (temp != null)
        {
            return encoder.matches(user.getPassword(),temp.getPassword()) ? ResponseEntity.ok(temp):
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body("Wrong Password");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Username is not found");
        }
    }
}
