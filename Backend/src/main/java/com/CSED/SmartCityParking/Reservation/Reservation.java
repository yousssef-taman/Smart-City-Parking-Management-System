package com.CSED.SmartCityParking.Reservation;
import com.CSED.SmartCityParking.Enums.ReservationStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private Integer ID;

    @Column(name = "spot_id")
    private Integer SpotID;

    @Column(name = "lot_id")
    private Integer LotID;

    @Column(name = "driver_id")
    private Integer DriverID;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @Column(name = "reservation_hours")
    private Integer reservationHours;

    @Column(name = "reservation_time")
    private LocalDateTime reservationTime;
}


