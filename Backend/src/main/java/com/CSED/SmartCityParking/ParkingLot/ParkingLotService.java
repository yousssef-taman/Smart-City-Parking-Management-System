package com.CSED.SmartCityParking.ParkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    public void saveParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.createLot(parkingLot.getLotName(), parkingLot.getLocation(), parkingLot.getCapacity()
                , parkingLot.getPricingStructure() , parkingLot.getManagerId());
    }

    public Optional<ParkingLot> getParkingLotById(Integer id) {
        return parkingLotRepository.findById(id);
    }

    public void deleteParkingLot(Integer id) {
        parkingLotRepository.deleteById(id);
    }

    public List<ParkingLot> searchLot(String location) {
        return  this.parkingLotRepository.searchLot(location);
    }

}

