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


    public void saveSpot(Spot spot, Integer capacity) {
        for (int i = 0; i < capacity; i++) {
            this.spotRepository.createSpot(spot.getLotId() , spot.getType());
        }
    }

    public List<Spot> getSpotsByLotId(Integer lotId) {
        return spotRepository.getSpotsByLotId(lotId);
    }
}