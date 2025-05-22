package com.CSED.SmartCityParking.Spot;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service class
@Service
public class SpotService {

    private final SpotDao spotDao;

    @Autowired
    public SpotService(SpotDao spotDao) {
        this.spotDao = spotDao;
    }


    public Integer createSpot(Spot spot) {
        try {
            return this.spotDao.createSpot(spot);
        } catch (DataAccessException dataAccessException) {
            return -1;
        }
    }


    @Transactional
    public List<Integer> createManySpots(Spot spot, Integer capacity) {
        List<Integer> ids = new ArrayList<>();
        try {
            for (int i = 0; i < capacity; i++) {
                ids.add(this.spotDao.createSpot(spot));
            }
            return ids;
        } catch (DataAccessException dataAccessException) {
            return null;
        }
    }

    public Boolean reserveSpot(Integer spotID, Integer lotID) {
        try {
            this.spotDao.reserveSpot(spotID, lotID);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }
    public Boolean occupySpot(Integer spotID, Integer lotID) {
        try {
            this.spotDao.occupySpot(spotID, lotID);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public Boolean leaveSpot(Integer spotID, Integer lotID) {
        try {
            this.spotDao.leaveSpot(spotID, lotID);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }




}