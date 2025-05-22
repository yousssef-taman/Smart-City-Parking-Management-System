package com.CSED.SmartCityParking.Reservation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private Integer ID;
    private Integer SpotID;
    private Integer LotID;
    private Integer DriverID;
    private ReservationStatus reservationStatus;
    private Integer howManyHours;
    private LocalDateTime startTime;
}


