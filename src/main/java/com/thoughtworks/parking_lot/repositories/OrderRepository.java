package com.thoughtworks.parking_lot.repositories;

import com.thoughtworks.parking_lot.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
    @Query("Select u from Orders u where u.plateNumber = :plateNumber")
    Orders findPlateNumber(@Param("plateNumber") String plateNumber);
}
