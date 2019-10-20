package com.thoughtworks.parking_lot.parkingLot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
    @Query("Select u from ParkingLot u where u.name = :name")
    Optional<ParkingLot> findByName(@Param("name") String name);
//    @Query("Select c from Company c where c.name = :name")
//    Optional<Company> findByName(@Param("name") String name);
//
//    Optional<Company> findByNameContaining(String name);
}
