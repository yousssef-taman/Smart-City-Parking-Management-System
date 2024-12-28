package com.CSED.SmartCityParking.Driver;


import com.CSED.SmartCityParking.Reservation.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE driver_id = :driver_id" , nativeQuery = true)
    List<Reservation> getDriverReservationsByID(@Param("driver_id") Integer DriverID);


    @Query(value = "SELECT p.* FROM reservation as r join penalities as p on  " +
            "r.id = p.reservation_id WHERE r.driver_id = :driverid;" , nativeQuery = true)
    List<Penalities> getPenaltiesByDriverID(@Param("driverid") Integer DriverID);


    @Query(value = "INSERT INTO penalities values (:reservationid ,:penaltyFee)", nativeQuery = true)
    @Modifying
    @Transactional
    void setPenaltyFeeToReservation(@Param("reservationid") Integer reservationID ,@Param("penaltyFee") float penaltyFee);
}
