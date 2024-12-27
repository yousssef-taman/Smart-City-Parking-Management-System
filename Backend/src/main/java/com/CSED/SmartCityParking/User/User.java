package com.CSED.SmartCityParking.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "SmartParking")
@Setter
@Getter
public class User {
    @Id
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;

}
