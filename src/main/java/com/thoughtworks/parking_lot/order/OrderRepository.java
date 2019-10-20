package com.thoughtworks.parking_lot.order;

import com.thoughtworks.parking_lot.car.Car;
import com.thoughtworks.parking_lot.parkingLot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
    @Query("Select u from Orders u where u.plateNumber = :plateNumber")
    Orders findPlateNumber(@Param("plateNumber") String plateNumber);
}
