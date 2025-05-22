package com.CSED.SmartCityParking.Spot;

import com.CSED.SmartCityParking.Reservation.ReservationStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;

@Repository
public class SpotDao {

    private final JdbcTemplate jdbcTemplate ;

    public SpotDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private Spot rowMapper(ResultSet rs, Integer rowMapper) throws SQLException {
        return new Spot(
                rs.getInt(1),
                rs.getInt(2),
                SpotStatus.valueOf(rs.getString(3).toUpperCase()),
                SpotType.valueOf(rs.getString(4).toUpperCase()),
                rs.getFloat(5)
        );
    }

    public Integer  createSpot(Spot spot) {
        String sql = "INSERT INTO spot(lot_id , status , type, price) VALUES (? , ?, ? , ?) ;";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                conn ->
                {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, spot.getLotId());
                    ps.setString(2, spot.getStatus().toString());
                    ps.setString(3, spot.getType().toString());
                    ps.setFloat(4, spot.getPrice());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
    }


    public void reserveSpot(Integer spotID, Integer lotID) {
        String sql = "UPDATE spot SET status = RESERVED WHERE spotID=? AND lotID = ?;";
        jdbcTemplate.update(sql, spotID, lotID);
    }

    public void occupySpot(Integer spotID, Integer lotID) {
        String sql = "UPDATE spot SET status = OCCUPIED WHERE spotID=? AND lotID = ?;";
        jdbcTemplate.update(sql, spotID, lotID);
    }
    public void leaveSpot(Integer spotID, Integer lotID) {
        String sql = "UPDATE spot SET status = AVAILABLE WHERE spotID=? AND lotID = ?;";
        jdbcTemplate.update(sql, spotID, lotID);
    }




}
