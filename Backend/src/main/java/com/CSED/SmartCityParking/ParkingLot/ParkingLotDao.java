package com.CSED.SmartCityParking.ParkingLot;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ParkingLotDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParkingLotDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ParkingLot rowMapper(ResultSet rs, Integer rowNum) throws SQLException {
        return new ParkingLot(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5)
        ) ;
    }

    public ParkingLot getLotById(Integer id) {
        String sql = "SELECT * FROM parking_lot WHERE id = ? ; ";
        return this.jdbcTemplate.queryForObject(sql, this::rowMapper, id);

    }

    public List<ParkingLot> getAllLotsByLocation(String location) {
        String sql = "SELECT * FROM parking_lot WHERE location = ?;";
        return this.jdbcTemplate.query(sql, this::rowMapper, location);
    }

    public List<ParkingLot> getAllParkingLots() {
        String sql = "SELECT * FROM smartparking.parking_lot;";
        return this.jdbcTemplate.query(sql, this::rowMapper);
    }

    public void deleteLotById(Integer id) {
        String sql = "DELETE FROM parking_lot WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public Integer saveLot(ParkingLot parkingLot) {
        String sql = "INSERT INTO parking_lot (manager_id, lot_name, location , capacity ) VALUES (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingLot.getManagerId());
            ps.setString(2, parkingLot.getLotName());
            ps.setString(3, parkingLot.getLocation());
            ps.setInt(4, parkingLot.getCapacity());
            return ps;
        }, keyHolder);

        return keyHolder.getKey()!=null ? keyHolder.getKey().intValue() : -1 ;
    }
}
