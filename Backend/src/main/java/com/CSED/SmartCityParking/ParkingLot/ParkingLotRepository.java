package com.CSED.SmartCityParking.ParkingLot;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "INSERT INTO SmartParking.parking_lot(lot_name, location, capacity,pricing_structure) " +
                    "VALUES(:lotName, :location, :capacity, :pricing)"
    )
    void createLot(@Param("lotName") String lotName,
                   @Param("location") String location,
                   @Param("capacity") Integer capacity,
                   @Param("pricing") Integer pricing);


      @Query(
            value = "SELECT * FROM SmartParking.parking_lot  WHERE SmartParking.parking_lot.location LIKE :location",
            nativeQuery = true
    )
    List<ParkingLot> searchLot(@Param("location") String location);


}
