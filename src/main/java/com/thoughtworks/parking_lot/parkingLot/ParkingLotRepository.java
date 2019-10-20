package com.thoughtworks.parking_lot.parkingLot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
}
