package com.CSED.SmartCityParking.ParkingLot;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;

import java.util.List;


@Repository
public class ParkingLotDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParkingLotDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ParkingLot getLotById(Integer id) {
        String query = "SELECT * FROM parking_lot WHERE id = ? ; ";
        return this.jdbcTemplate.queryForObject(query, (rs, rowNum) -> new ParkingLot(
                rs.getInt(1), rs.getString(1), rs.getString(2), rs.getInt(3),
                rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)
        ), id);

    }

    public List<ParkingLot> getAllLotsByLocation(String location) {
        String query = "SELECT * FROM parking_lot WHERE location = ?;";
        return this.jdbcTemplate.query(query, (rs, rowNum) -> new ParkingLot(
                        rs.getInt(1), rs.getString(1), rs.getString(2), rs.getInt(3),
                        rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)),
                location);
    }

    public List<ParkingLot> getAllParkingLots() {
        String query = "SELECT * FROM smartparking.parking_lot;";
        System.out.println("started");
        return this.jdbcTemplate.query(query, (rs, rowNum) -> new ParkingLot(
                rs.getInt(1), rs.getString(1), rs.getString(2), rs.getInt(3),
                rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
    }

    public void deleteLotById(Integer id) {
        String query = "DELETE FROM parking_lot WHERE id = ?";
        jdbcTemplate.update(query, id);
    }


    public Number saveLot(ParkingLot parkingLot) {
        String query = "INSERT INTO parking_lot (manager_id, lot_name, capacity, pricing_structure, start_peek_time, end_peek_time, price_multiplier) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setInt(1, parkingLot.getManagerId());
            ps.setString(2, parkingLot.getLotName());
            ps.setInt(3, parkingLot.getCapacity());
            ps.setFloat(4, parkingLot.getPricingStructure());
            ps.setInt(5, parkingLot.getStartPeekTime());
            ps.setInt(6, parkingLot.getEndPeekTime());
            ps.setInt(7, parkingLot.getPriceMultiplier());
            return ps;
        }, keyHolder);

        return keyHolder.getKey();
    }
}
