package com.CSED.SmartCityParking.Driver;

import com.CSED.SmartCityParking.Reservation.Reservation;
import com.CSED.SmartCityParking.Spot.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverServices driverServices;

    @GetMapping("/reservations/{DriverID}")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable Integer DriverID) {
        List<Reservation> reservations = driverServices.getDriverReservations(DriverID);
        return reservations.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveSpot(@RequestBody Reservation reservation) {
        Reservation createdReservation = driverServices.reserveDriverSpot(reservation);
        if (createdReservation == null) {
            return new ResponseEntity<>("Invalid reservation details", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/penalities/{DriverID}")
    public ResponseEntity<List<Penalities>> getPenalties(@PathVariable Integer DriverID) {
        List<Penalities> penalties = driverServices.getPenaltiesByDriverID(DriverID);
        return penalties.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(penalties, HttpStatus.OK);
    }

    @PutMapping("/leavespot")
    public ResponseEntity<?> leaveSpot(@RequestParam Integer ReservationID) {
        driverServices.checkForPenality(ReservationID);
        return ResponseEntity.ok("Penality has been checked");
    }
}
