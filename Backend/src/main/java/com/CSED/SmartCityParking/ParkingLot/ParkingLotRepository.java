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
            value = "INSERT INTO SmartParking.parkinglot(LotName, location, capacity,pricingStructure,ManagerID, startPeekTime , endPeekTime , priceMultiplier) " +
                    "VALUES(:lotName, :location, :capacity, :pricing ,:managerId ,:startPeekTime , :endPeekTime , :pricingMultiplier)"
    )
    void createLot(@Param("lotName") String lotName,
                   @Param("location") String location,
                   @Param("capacity") Integer capacity,
                   @Param("pricing") Float pricing,
                   @Param("managerId") Integer manager_id,
                   @Param("startPeekTime") Integer startPeekTime ,
                   @Param("endPeekTime") Integer endPeekTime ,
                   @Param("pricingMultiplier") Integer pricingMultiplier) ;


                   @Query(
                           value = "SELECT * FROM SmartParking.parkinglot  WHERE SmartParking.parking_lot.location LIKE :location",
                           nativeQuery = true
                   )
                   List<ParkingLot> searchLot(@Param("location") String location);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Integer getLastInsertId();


}
