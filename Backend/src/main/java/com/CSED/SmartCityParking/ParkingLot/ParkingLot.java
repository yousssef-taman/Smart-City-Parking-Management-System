package com.CSED.SmartCityParking.ParkingLot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_lot", schema = "smartparking")
@Setter
@Getter
public class ParkingLot {

    @Id
    private Integer id;

    @Column
    private Integer manager_id;

    @Column
    private String lotName;

    @Column
    private String location;

    @Column
    private Integer capacity;

    @Column
    private Integer pricingStructure;
}
