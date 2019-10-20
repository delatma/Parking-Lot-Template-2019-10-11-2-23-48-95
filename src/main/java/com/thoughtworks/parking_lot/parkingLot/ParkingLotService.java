package com.thoughtworks.parking_lot.parkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public Iterable<ParkingLot> viewAllParkingLots(ParkingLot parkingLot) {
        return parkingLotRepository.findAll();
    }

    public boolean delete(String name) {
        Optional<ParkingLot> optionalCompany = findByName(name);
        if (optionalCompany.isPresent()) {
            ParkingLot existingParkingLot = optionalCompany.get();
            parkingLotRepository.delete(existingParkingLot);
            return true;
        }
        return false;
    }

    Optional<ParkingLot> findByName(String name) {
        return parkingLotRepository.findByName(name);
    }

    public Iterable<ParkingLot> listAllParkingLots(PageRequest pageRequest) {
        return parkingLotRepository.findAll(pageRequest);
    }
}
