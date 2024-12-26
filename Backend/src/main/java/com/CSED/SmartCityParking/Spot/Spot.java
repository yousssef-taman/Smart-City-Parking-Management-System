package com.CSED.SmartCityParking.Spot;

import jakarta.persistence.*;

@Entity
@Table(name = "spot" , schema = "smartparking")
public class Spot {

    @Id
    @Column(name = "SpotID")
    private Integer spotId;

    @Column(name = "LotID", nullable = false)
    private Integer lotId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false)
    private Type type;

    @Column(name = "Price", nullable = false)
    private Float price;


    public enum Status {
        OCCUPIED,
        AVAILABLE,
        RESERVED
    }

    public enum Type {
        REGULAR,
        DISABLED,
        EV
    }
}
