package com.CSED.SmartCityParking.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    @Autowired
    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }



    public Integer saveReservation(Reservation reservation) {
        try {
            return this.reservationDao.createReservation(reservation);
        } catch (DataAccessException e) {
            return -1;
        }
    }


    public Reservation getReservationById(Integer id) {
        try {
            return this.reservationDao.getReservationById(id);
        } catch (DataAccessException dataAccessException) {
            return null;
        }
    }

    public List<Reservation> getAllReservationsByManager(Integer managerID)
    {
        try {
            return this.reservationDao.getAllReservationsByManagerId(managerID);
        } catch (DataAccessException dataAccessException) {
            return null;
        }
    }


    public Boolean deleteReservation(Integer id) {
        try {
            this.reservationDao.deleteReservation(id);
            return true ;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }
}

