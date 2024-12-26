package com.CSED.SmartCityParking.ParkingLot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ParkingLot", schema = "smartparking")
@Setter
@Getter
public class ParkingLot {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "LotName")
    private String lotName;

    @Column(name = "Location")
    private String location;

    @Column(name = "Capacity")
    private Integer capacity;

    @Column(name = "PricingStructure")
    private Integer pricingStructure;
}
