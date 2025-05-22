package com.CSED.SmartCityParking.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Reservation rowMapper(ResultSet rs, Integer rowNum) throws SQLException {
        return new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                rs.getInt(4), ReservationStatus.valueOf(rs.getString(5)),
                rs.getInt(6), rs.getObject(7, LocalDateTime.class));
    }




    public Integer createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation(spot_id, lot_id, driver_id, reservation_status, reservation_hours, reservation_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reservation.getSpotID());
            ps.setInt(2, reservation.getLotID());
            ps.setInt(3, reservation.getDriverID());
            ps.setString(4, reservation.getReservationStatus().toString());
            ps.setInt(5, reservation.getHowManyHours());
            ps.setTimestamp(6, Timestamp.valueOf(reservation.getStartTime())); // JDBC 4.2+ handles LocalDateTime
            return ps;
        }, keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
    }

    public List<Reservation> getAllReservationsByDriverId(Integer driverID) {
        String sql = "SELECT id FROM reservations WHERE driver_id = ?";
        return jdbcTemplate.query(sql, this::rowMapper) ;

    }

    public List<Reservation> getAllReservationsByManagerId(Integer managerId) {
        String sql = "SELECT id FROM reservations WHERE manager_id = ?";
        return jdbcTemplate.query(sql, this::rowMapper) ;
    }


    public Reservation getReservationById(Integer reservationId) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
            return jdbcTemplate.queryForObject(sql,this::rowMapper) ;
    }

    public void deleteReservation(Integer id) {
        String query = "DELETE FROM reservation WHERE id=?;";
        jdbcTemplate.update(query , id) ;
    }

}
