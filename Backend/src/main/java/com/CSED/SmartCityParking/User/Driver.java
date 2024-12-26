package com.CSED.SmartCityParking.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "driver", schema = "smartparking")
@Setter
@Getter
public class Driver {
    @Id
    private Long id;

    @Column
    private String license;

}
