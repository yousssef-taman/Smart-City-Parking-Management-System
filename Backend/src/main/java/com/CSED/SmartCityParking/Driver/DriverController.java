package com.CSED.SmartCityParking.Driver;

import com.CSED.SmartCityParking.Reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverServices driverServices;


    @GetMapping("/{DriverID}")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable Integer DriverID) {
        List<Reservation> reservations = driverServices.getDriverReservations(DriverID);
        return reservations.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserveSpot(@RequestBody Reservation reservation) {
        Reservation createdReservation = driverServices.reserveDriverSpot(reservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
}
