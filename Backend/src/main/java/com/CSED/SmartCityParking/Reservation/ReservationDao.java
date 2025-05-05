package com.CSED.SmartCityParking.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Reservation> getAllReservationsByDriverId(Integer driverID) {
        String query = "SELECT id FROM reservations WHERE driver_id = ?";
        return jdbcTemplate.query(query, (rs, rowNumber) -> new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                ReservationStatus.valueOf(rs.getString(5)), rs.getInt(6), rs.getTime(7).toLocalTime()), driverID);

    }

    public List<Reservation> getAllReservationsByManagerId(Integer managerId) {
        String query = "SELECT id FROM reservations WHERE manager_id = ?";
        return jdbcTemplate.query(query, (rs, rowNumber) -> new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                ReservationStatus.valueOf(rs.getString(5)), rs.getInt(6), rs.getTime(7).toLocalTime()), managerId);

    }


    public Reservation getReservationById(Integer reservationId) {
        String query = "SELECT * FROM reservations WHERE id = ?";
            return jdbcTemplate.queryForObject(query, (rs, rowNumber) -> new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                    ReservationStatus.valueOf(rs.getString(5)), rs.getInt(6), rs.getTime(7).toLocalTime()), reservationId);
    }


    public void createReservation(Reservation reservation) {
        String query = "INSERT INTO smartparking.reservation(spot_id, lot_id, driver_id, reservation_status, reservation_hours, reservation_time)" +
                "VALUES ( ?, ?, ?, ?, ?, ?);" ;
        jdbcTemplate.update(query, reservation.getSpotID(), reservation.getLotID(), reservation.getDriverID(), reservation.getReservationStatus(), reservation.getReservationHours(), reservation.getReservationTime());
    }

    public void deleteReservation(Integer id) {
        String query = "DELETE FROM smartparking.reservation WHERE id=?;";
        jdbcTemplate.update(query , id) ;
    }
}
