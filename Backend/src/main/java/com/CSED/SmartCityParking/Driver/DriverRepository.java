package com.CSED.SmartCityParking.Driver;


import com.CSED.SmartCityParking.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE DriverID = :DriverID" , nativeQuery = true)
    List<Reservation> getDriverReservationsByID(@Param("DriverID") Integer DriverID);
}
