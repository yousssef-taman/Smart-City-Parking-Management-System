package com.CSED.SmartCityParking.Spot;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {


    private Integer spotId;
    private Integer lotId;
    private SpotStatus status;
    private SpotType type;
    private Float price;


}
