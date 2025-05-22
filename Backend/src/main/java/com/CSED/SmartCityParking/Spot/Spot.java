package com.CSED.SmartCityParking.Spot;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spot" , schema = "smartparking")
@Setter
@Getter
public class Spot {

    @Id
    @Column(name = "spot_id")
    private Integer spotId;

    @Column(name = "lotId", nullable = false)
    private Integer lotId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

//    @Column(name = "price", nullable = false)
//    private Float price;


    public enum Status {
        occupied,
        available,
        reserved,
        NULL
    }

    public enum Type {
        regular,
        disabled,
        EV,
        NULL
    }
}
