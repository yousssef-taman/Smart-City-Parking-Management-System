package com.CSED.SmartCityParking.Spot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/spot")
public class SpotController {

    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }


    @PostMapping("/create")
    public ResponseEntity<List<Integer>> createSpot(@RequestBody Spot spot, @RequestParam(value = "capacity") Integer capacity) {
        List<Integer> spotIDs = this.spotService.createManySpots(spot, capacity);
        if (spotIDs == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(spotIDs, HttpStatus.OK);
    }
    //get spots by lot id
    @GetMapping("/{lotId}")
    public List<Spot> getSpotsByLotId(@PathVariable("lotId") Integer lotId) {
        return spotService.getSpotsByLotId(lotId);
    }

}
