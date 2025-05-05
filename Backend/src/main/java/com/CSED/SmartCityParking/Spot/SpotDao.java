package com.CSED.SmartCityParking.Spot;

import com.CSED.SmartCityParking.Reservation.ReservationStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
@Repository
public class SpotDao {

    private final JdbcTemplate jdbcTemplate ;

    public SpotDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Integer reserveSpot(Integer spotID, Integer lotID, Integer driverID,
                               ReservationStatus reservationStatus, Integer reservationHours,
                               LocalTime reservationTime) {
        String query = "INSERT INTO reservation VALUES(?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(query, spotID, lotID, driverID, reservationStatus, reservationHours, reservationTime);
            return jdbcTemplate.query(
                    "SELECT id FROM reservation ORDER BY id DESC LIMIT 1",
                    rs -> rs.next() ? rs.getInt("id") : null
            );
        } catch (DataAccessException dataAccessException) {
            return -1;
        }
    }


    public void createSpot(Spot spot) {
        String query = "INSERT INTO spot(lot_id , status , type) VALUES (? , ? , ?) ;";
        jdbcTemplate.update(query, spot.getLotId(), spot.getStatus(), spot.getType());
    }
}
