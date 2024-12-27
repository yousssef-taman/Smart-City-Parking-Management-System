package com.CSED.SmartCityParking.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    @Query(value = "INSERT INTO reservation (SpotID, LotID, DriverID, ReservationStatus, ReservationHours, ReservationTime) " +
            "VALUES (:spotId, :lotId, :driverId, :reservationStatus, :reservationHours, :reservationTime)", nativeQuery = true)
    @Modifying
    void reserveSpot(@Param("spotId") Integer spotId,
                     @Param("lotId") Integer lotId,
                     @Param("driverId") Integer driverId,
                     @Param("reservationStatus") ReservationStatus reservationStatus,
                     @Param("reservationHours") Integer reservationHours,
                     @Param("reservationTime") LocalDateTime reservationTime);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Integer getLastInsertId();


    @Query(value = "SELECT * FROM reservation WHERE ID = :id", nativeQuery = true)
    Reservation getReservationById(Integer id);
}