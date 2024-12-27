package com.CSED.SmartCityParking.Reservation;

import com.CSED.SmartCityParking.Enums.ReservationStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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


    @Query(value = "SELECT r.* FROM" +
            " parking_lot as p join reservation as r on p.id = r.lot_id where p.manager_id = :id ;", nativeQuery = true)
    List<Reservation> getReservationByManagerID(@Param("id") Integer id);


    @Query(value = "UPDATE reservation SET reservation_status = :reservationstatus WHERE id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void updateReservationStatus(@Param("id") Integer reservationID,@Param("reservationstatus")ReservationStatus reservationStatus);

    @Query(value = "DELETE FROM reservation WHERE id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteReservationByID(@Param("id") Integer id);
    @Query(value = "SELECT * FROM reservation WHERE ID = :id", nativeQuery = true)
    Reservation getReservationById(Integer id);


}