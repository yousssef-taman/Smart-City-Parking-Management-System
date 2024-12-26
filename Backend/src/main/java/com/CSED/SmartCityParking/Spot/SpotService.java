package com.CSED.SmartCityParking.Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service class
@Service
public class SpotService {

    private final SpotRepository spotRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }

    public Optional<Spot> getSpotById(Integer spotId) {
        return spotRepository.findById(spotId);
    }

    public Spot saveSpot(Spot spot) {
        return spotRepository.save(spot);
    }

    public void deleteSpot(Integer spotId) {
        spotRepository.deleteById(spotId);
    }
}