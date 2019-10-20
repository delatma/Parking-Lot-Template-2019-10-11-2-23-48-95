package com.thoughtworks.parking_lot.order;

import com.thoughtworks.parking_lot.parkingLot.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order newOrder) {
        return orderRepository.save(newOrder);
    }
}
