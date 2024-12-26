package com.CSED.SmartCityParking.User;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAndDriver {
    
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String role;

    private String license;

    public UserAndDriver(Integer id ,String username , String password ,String email ,String role ,String license) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.license = license;
    }
}
