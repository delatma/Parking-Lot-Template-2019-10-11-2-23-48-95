package com.thoughtworks.parking_lot.parkingLot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
//    @Query("Select c from Company c where c.name = :name")
//    Optional<Company> findByName(@Param("name") String name);
//
//    Optional<Company> findByNameContaining(String name);
}
