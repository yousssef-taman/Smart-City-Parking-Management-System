package com.CSED.SmartCityParking.ParkingLot;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {


    private Integer id;
    private Integer managerId;
    private String lotName;
    private String location;
    private Integer capacity;

}
