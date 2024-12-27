package com.CSED.SmartCityParking.Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spot")
public class SpotController {

    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createSpot(@RequestBody Spot spot) {
        try {
            System.out.println(spot.getStatus());
            System.out.println(spot.getType());
            spotService.saveSpot(spot) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Spot created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

}
