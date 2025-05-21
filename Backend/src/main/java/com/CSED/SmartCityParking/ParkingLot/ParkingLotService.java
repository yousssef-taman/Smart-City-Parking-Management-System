package com.CSED.SmartCityParking.ParkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ParkingLotService {

    private final ParkingLotDao parkingLotDao ;
    @Autowired
    public ParkingLotService(ParkingLotDao parkingLotDao) {

        this.parkingLotDao = parkingLotDao;
    }

    public List<ParkingLot> getAllParkingLots() {
        try {
          return   this.parkingLotDao.getAllParkingLots();
        } catch (DataAccessException dataAccessException) {
            return null  ;
        }
    }

    public ParkingLot getParkingLotById(Integer id) {

        try {
            return this.parkingLotDao.getLotById(id);
        } catch (DataAccessException dataAccessException) {
            return null ;
        }
    }

    public Integer saveParkingLot(ParkingLot parkingLot) {
        try {
            return this.parkingLotDao.saveLot(parkingLot);
        } catch (DataAccessException dataAccessException) {
            return -1 ;
        }
    }



    public Boolean deleteParkingLot(Integer id) {

        try {
            this.parkingLotDao.deleteLotById(id);
            return true ;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public List<ParkingLot> searchLot(String location) {
        try {
            return this.parkingLotDao.getAllLotsByLocation(location);
        } catch (DataAccessException dataAccessException) {
            return null ;
        }

    }

}

