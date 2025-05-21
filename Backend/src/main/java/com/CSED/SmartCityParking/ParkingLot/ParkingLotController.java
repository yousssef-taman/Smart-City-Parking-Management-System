package com.CSED.SmartCityParking.ParkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
        List<ParkingLot> parkingLots = this.parkingLotService.getAllParkingLots();
        if (parkingLots == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable("id") Integer id) {
        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        if (parkingLot == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

    @PostMapping("/createlot")
    public ResponseEntity<Integer> createParkingLot(@RequestBody ParkingLot parkingLot) {
         Integer id =  parkingLotService.saveParkingLot(parkingLot);
        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteParkingLot(@PathVariable("id") Integer id) {

        Boolean deletionStatus = parkingLotService.deleteParkingLot(id);
        if (deletionStatus == false) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("searchLot")
    public ResponseEntity<List<ParkingLot>> getLots(@Param(value = "location") String location) {
        List<ParkingLot> lots = this.parkingLotService.searchLot(location);
        if (lots == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
}
