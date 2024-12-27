package com.CSED.SmartCityParking.Reservation;

import com.CSED.SmartCityParking.Enums.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.getReservationById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Integer id) {
        reservationRepository.deleteReservationByID(id);
    }

    public List<Reservation> getAllReservationsByManager(Integer id)
    {
        return reservationRepository.getReservationByManagerID(id);
    }

    public Reservation updateReservationStatus(Integer reservationID, ReservationStatus reservationStatus)
    {
        reservationRepository.updateReservationStatus(reservationID,reservationStatus);
         return reservationRepository.getReservationById(reservationID);
    }
}

