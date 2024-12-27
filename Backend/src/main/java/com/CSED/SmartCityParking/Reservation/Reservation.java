package com.CSED.SmartCityParking.Reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Entity
@Table(name = "reservation", schema = "smartparking")
@Setter
@Getter
public class Reservation {

    @Id
    private Integer ID;

    @Column(name = "SpotID")
    private Integer SpotID;

    @Column(name = "LotID")
    private Integer LotID;

    @Column(name = "DriverID")
    private Integer DriverID;

    @Enumerated(EnumType.STRING)
    @Column(name = "ReservationStatus")
    private ReservationStatus reservationStatus;

    @Column(name = "ReservationHours")
    private Integer reservationHours;

    @Column(name = "ReservationTime")
    private LocalDateTime reservationTime;
}


