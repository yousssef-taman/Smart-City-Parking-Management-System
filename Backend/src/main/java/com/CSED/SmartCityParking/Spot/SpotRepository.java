package com.CSED.SmartCityParking.Spot;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository  extends JpaRepository<Spot, Integer> {


    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO SmartParking.spot(lot_id , status , type) VALUES\n" +
                    "(:lot_id , 2 , :type ); "
            , nativeQuery = true
    )
    void createSpot(@Param(value = "lot_id")Integer lot_id ,
                    @Param(value = "type") Spot.Type type
                    );
}
