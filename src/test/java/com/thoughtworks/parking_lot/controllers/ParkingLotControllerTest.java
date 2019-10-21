package com.thoughtworks.parking_lot.controllers;

import com.thoughtworks.parking_lot.entities.Orders;
import com.thoughtworks.parking_lot.repositories.OrderRepository;
import com.thoughtworks.parking_lot.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static javafx.beans.binding.Bindings.when;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
class ParkingLotControllerTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void should_add_a_new_order() {
    }

    @Test
    void should_listParkingLotsByPage() {
    }

    @Test
    void should_listSingleParkingLot() {
    }

    @Test
    void should_updateParkingLot() {
    }

    @Test
    void should_delete_parkingLot() {
    }

    @Test
    void should_createOrder() {
    }

    @Test
    void should_updateOrder() {
    }
}