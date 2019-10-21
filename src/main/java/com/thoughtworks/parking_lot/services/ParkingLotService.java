package com.thoughtworks.parking_lot.services;

import com.thoughtworks.parking_lot.entities.ParkingLot;
import com.thoughtworks.parking_lot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        ParkingLot parkingLot = parkingLotRepository.findByName(name);
        if (parkingLot != null) {
            parkingLotRepository.delete(parkingLot);
            return true;
        }
        return false;
    }

    public ParkingLot findByName(String name) {
        return parkingLotRepository.findByName(name);
    }

    public Iterable<ParkingLot> listAllParkingLots(PageRequest pageRequest) {
        return parkingLotRepository.findAll(pageRequest);
    }
}
