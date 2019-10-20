package com.thoughtworks.parking_lot.parkingLot;

import com.thoughtworks.parking_lot.order.Orders;

import javax.persistence.*;

@Entity
public class ParkingLot {
    @Id
    private String name;
    private Integer capacity;
    private String location;

    @OneToOne
    private Orders orders;

    public ParkingLot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

}
