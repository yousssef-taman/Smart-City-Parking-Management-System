package com.CSED.SmartCityParking.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        return (reservation != null)
                ? new ResponseEntity<>(reservation, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<Reservation>> getAllReservationsByManagerID(@PathVariable Integer managerID) {
        List<Reservation> reservationList = this.reservationService.getAllReservationsByManager(managerID);
        return reservationList != null ? new ResponseEntity<>(reservationList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Integer> createReservation(@RequestBody Reservation reservation) {
        Integer reservationID = reservationService.saveReservation(reservation);
        if (reservationID != -1) {
            return new ResponseEntity<>(reservationID, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Integer id) {
        Boolean deleteStatus = this.reservationService.deleteReservation(id);
        return deleteStatus ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


