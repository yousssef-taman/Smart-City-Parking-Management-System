package com.CSED.SmartCityParking.ParkingLot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_lot", schema = "SmartParking")
@Setter
@Getter
public class ParkingLot {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "lot_name")
    private String lotName;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "pricing_structure")
    private Float pricingStructure;


    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "start_peek_time")
    private Integer startPeekTime;

    @Column(name = "end_peek_time")
    private Integer endPeekTime;

    @Column(name = "price_multiplier")
    private Integer priceMultiplier ;
}
