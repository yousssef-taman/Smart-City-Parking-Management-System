package com.CSED.SmartCityParking.Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spot")
public class SpotController {

    private final SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping
    public List<Spot> getAllSpots() {
        return spotService.getAllSpots();
    }

    @GetMapping("/{id}")
    public Optional<Spot> getSpotById(@PathVariable("id") Integer id) {
        return spotService.getSpotById(id);
    }

    @PostMapping
    public Spot createSpot(@RequestBody Spot spot) {
        return spotService.saveSpot(spot);
    }

    @DeleteMapping("/{id}")
    public void deleteSpot(@PathVariable("id") Integer id) {
        spotService.deleteSpot(id);
    }
}
