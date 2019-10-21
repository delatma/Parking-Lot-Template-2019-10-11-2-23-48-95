package com.thoughtworks.parking_lot.services;

import com.thoughtworks.parking_lot.entities.Orders;
import com.thoughtworks.parking_lot.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders save(Orders newOrders) {
        orderRepository.save(newOrders);
        return newOrders;
    }

    public Orders findPlateNumber(String plateNumber) {
        return orderRepository.findPlateNumber(plateNumber);
    }
}
