package com.CSED.SmartCityParking.Driver;
import com.CSED.SmartCityParking.Enums.ReservationStatus;
import com.CSED.SmartCityParking.Reservation.Reservation;
import com.CSED.SmartCityParking.Reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DriverServices {


    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getDriverReservations(Integer DriverID)
    {
        return driverRepository.getDriverReservationsByID(DriverID);
    }

    public Reservation reserveDriverSpot(Reservation reservation) {
        reservationRepository.reserveSpot(reservation.getSpotID() , reservation.getLotID() ,
                reservation.getDriverID(), ReservationStatus.PENDING , reservation.getReservationHours() , reservation.getReservationTime());
        reservation.setID(reservationRepository.getLastInsertId());
        return reservation;
    }
}
