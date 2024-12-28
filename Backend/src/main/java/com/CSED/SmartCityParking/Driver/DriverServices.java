package com.CSED.SmartCityParking.Driver;
import com.CSED.SmartCityParking.Enums.ReservationStatus;
import com.CSED.SmartCityParking.ParkingLot.ParkingLot;
import com.CSED.SmartCityParking.ParkingLot.ParkingLotRepository;
import com.CSED.SmartCityParking.Reservation.Reservation;
import com.CSED.SmartCityParking.Reservation.ReservationRepository;
import com.CSED.SmartCityParking.Spot.Spot;
import com.CSED.SmartCityParking.Spot.SpotRepository;
import com.CSED.SmartCityParking.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DriverServices {


    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private SpotRepository spotRepository;

    public List<Reservation> getDriverReservations(Integer DriverID) {
        return driverRepository.getDriverReservationsByID(DriverID);
    }

    public Reservation reserveDriverSpot(Reservation reservation) {

        List<Reservation> reservedSpotsByID = reservationRepository.getReservationsBySpotID(reservation.getSpotID());

        if (!reservedSpotsByID.isEmpty()) {
            for (Reservation reservationIterator : reservedSpotsByID) {
                if (reservationIterator.getReservationTime().equals(reservation.getReservationTime()) && !reservationIterator.getReservationStatus().equals(ReservationStatus.Pending)) {
                    System.out.println("Matching reservation time found: " + reservation.getReservationTime());
                    return null;
                }
            }
        }
        ParkingLot parkingLot = parkingLotRepository.getLotById(reservation.getLotID());
        reservationRepository.reserveSpot(reservation.getSpotID(), reservation.getLotID(),
                reservation.getDriverID(), ReservationStatus.Pending, reservation.getReservationHours(), reservation.getReservationTime(),
                parkingLot.getPricingStructure() * reservation.getReservationHours());
        Integer currentId = reservationRepository.getLastInsertId();
        return reservationRepository.getReservationById(currentId);

    }


    public List<Penalities> getPenaltiesByDriverID(Integer DriverID)
    {
        return driverRepository.getPenaltiesByDriverID(DriverID);
    }

    public void checkForPenality(Integer reservationID) {

        Reservation reservation = reservationRepository.getReservationById(reservationID);

        LocalDateTime reservationTime = reservation.getReservationTime().plusHours(
                Long.valueOf(reservation.getReservationHours())); // Assuming a getter exists

        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isAfter(reservationTime)) {

            double penaltyFee = calculatePenalty(reservationTime, currentTime);
            driverRepository.setPenaltyFeeToReservation(reservationID ,(float)penaltyFee);
            System.out.println("Penalty applied: " + penaltyFee);
        } else {
            System.out.println("No penalty applied.");
        }

        spotRepository.UpdateSpotStatus(reservation.getSpotID(), Spot.Status.available);
    }

    private double calculatePenalty(LocalDateTime reservationTime, LocalDateTime currentTime) {
        long hoursExceeded = java.time.Duration.between(reservationTime, currentTime).toHours();
        return hoursExceeded * 10.0;
    }

}
