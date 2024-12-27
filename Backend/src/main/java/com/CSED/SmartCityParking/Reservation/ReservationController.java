package com.CSED.SmartCityParking.Reservation;
import com.CSED.SmartCityParking.Enums.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

//    @GetMapping
//    public ResponseEntity<List<Reservation>> getAllReservations() {
//        List<Reservation> reservations = reservationService.getAllReservations();
//        return reservations.isEmpty()
//                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
//                : new ResponseEntity<>(reservations, HttpStatus.OK);
//    }
//
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        return (reservation != null)
                ? new ResponseEntity<>(reservation, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
//    @PostMapping
//    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
//        Reservation savedReservation = reservationService.saveReservation(reservation);
//        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
//    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/manager/{id}")
    public ResponseEntity<List<Reservation>> getAllReservationsByManagerID(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.getAllReservationsByManager(id));
    }

    @PutMapping("/manager/update")
    public ResponseEntity<?> updateReservation(@RequestParam Integer reservationID,@RequestParam ReservationStatus reservationStatus) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(reservationID,reservationStatus));
    }
}


