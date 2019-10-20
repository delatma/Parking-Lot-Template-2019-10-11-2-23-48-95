package com.thoughtworks.parking_lot.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Orders newOrders) {
        orderRepository.save(newOrders);
    }

    public Orders findPlateNumber(String plateNumber) {
        return orderRepository.findPlateNumber(plateNumber);
    }
}
