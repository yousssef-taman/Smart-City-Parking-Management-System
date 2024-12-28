package com.CSED.SmartCityParking.Spot;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository  extends JpaRepository<Spot, Integer> {


    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO SmartParking.spot(lot_id , status , type) VALUES\n" +
                    "(:lotID , 2 , :type ); "
            , nativeQuery = true
    )
    void createSpot(@Param(value = "lotID")Integer lotID,
                    @Param(value = "type") Spot.Type type
                    );

    @Query(
            value = "SELECT * FROM SmartParking.spot WHERE lot_id = :lotId"
            , nativeQuery = true
    )
    List<Spot> getSpotsByLotId(@Param(value = "lotId") Integer lotId);
}
