package com.CSED.SmartCityParking.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    @GetMapping("/{id}")
    public Optional<ParkingLot> getParkingLotById(@PathVariable("id") Integer id) {
        return parkingLotService.getParkingLotById(id);
    }

    @PostMapping
    public void createParkingLot(@RequestBody ParkingLot parkingLot) {
         parkingLotService.saveParkingLot(parkingLot);
    }

    @DeleteMapping("/{id}")
    public void deleteParkingLot(@PathVariable("id") Integer id) {
        parkingLotService.deleteParkingLot(id);
    }


    @GetMapping("searchLot")
    public ResponseEntity<List<ParkingLot>> getLots(@Param(value = "location") String location) {
        List<ParkingLot> lots = this.parkingLotService.searchLot(location);
        if (lots == null || lots.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
}
