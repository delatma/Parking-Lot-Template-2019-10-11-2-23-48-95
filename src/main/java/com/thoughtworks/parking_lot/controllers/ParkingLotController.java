package com.thoughtworks.parking_lot.controllers;

import com.thoughtworks.parking_lot.entities.Car;
import com.thoughtworks.parking_lot.entities.Orders;
import com.thoughtworks.parking_lot.entities.ParkingLot;
import com.thoughtworks.parking_lot.services.OrderService;
import com.thoughtworks.parking_lot.services.ParkingLotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private OrderService orderService;

    @PostMapping(produces = {"application/json"})
    public HttpEntity<ParkingLot> add(@RequestBody ParkingLot parkingLot) {
        return new ResponseEntity<>(parkingLotService.save(parkingLot), HttpStatus.CREATED);
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<ParkingLot> listParkingLotsByPage(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "15") Integer pageSize) {
        Sort.Order orderByName = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
        return parkingLotService.listAllParkingLots(PageRequest.of(page, pageSize, Sort.by(orderByName)));
    }

    @GetMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ParkingLot>
    listSingleParkingLot(@PathVariable String name) throws NotFoundException {
        ParkingLot foundParkingLot = parkingLotService.findByName(name);
        if (foundParkingLot != null) {
            return new ResponseEntity<ParkingLot>(foundParkingLot, HttpStatus.OK);
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

    @PatchMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<ParkingLot>>
    updateParkingLot(@PathVariable String name, @RequestBody ParkingLot parkingLot) throws NotFoundException {
        ParkingLot searchParkingLot = parkingLotService.findByName(name);
        if (searchParkingLot != null) {
            searchParkingLot.setCapacity(parkingLot.getCapacity());
            parkingLotService.save(searchParkingLot);
            return new ResponseEntity<Optional<ParkingLot>>(Optional.of(searchParkingLot), HttpStatus.OK);
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

    @DeleteMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable String name) throws NotFoundException {
        boolean wasDeleted = parkingLotService.delete(name);
        if (wasDeleted) {
            return new ResponseEntity<>("Parking lot `" + name + "` deleted successfully!", HttpStatus.OK);
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

    @PostMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Orders>
    createOrder(@PathVariable String name, @RequestBody Car car) throws NotFoundException {
        Orders plateNumber = orderService.findPlateNumber(car.getPlateNumber());
        ParkingLot parkingLot = parkingLotService.findByName(name);
        if (parkingLot != null) {
            if (parkingLot.getCapacity() >= 1) {
                if (plateNumber == null) {
                    Orders newOrder = new Orders();
                    newOrder.setParkingLotName(name);
                    newOrder.setPlateNumber(car.getPlateNumber());
                    newOrder.setCreationTime(new Timestamp(new Date().getTime()));
                    newOrder.setCloseTime(null);
                    newOrder.setOrderStatus("Open");
                    orderService.save(newOrder);
                    parkingLot.setOrders(newOrder);
                    parkingLot.setCapacity(parkingLot.getCapacity() - 1);
                    parkingLotService.save(parkingLot);
                    return new ResponseEntity<Orders>(newOrder, HttpStatus.OK);
                }
                throw new NotFoundException(plateNumber + " already has an order!");
            }
            throw new NotFoundException("The parking lot is full!");
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }


    @PatchMapping(value = "/{name}/orders/{plateNumber}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Orders>
    updateOrder(@PathVariable String name, @PathVariable String plateNumber) throws NotFoundException {
        Orders existingOrder = orderService.findPlateNumber(plateNumber);
        ParkingLot parkingLot = parkingLotService.findByName(name);
        if (parkingLot != null) {
            if (existingOrder != null && existingOrder.getOrderStatus().equals("Open")) {
                existingOrder.setCloseTime(new Timestamp(new Date().getTime()));
                existingOrder.setOrderStatus("Close");
                orderService.save(existingOrder);
                parkingLot.setOrders(existingOrder);
                parkingLotService.save(parkingLot);
                return new ResponseEntity<Orders>(existingOrder, HttpStatus.OK);
            }
            throw new NotFoundException("Order does not exist for " + plateNumber + "!");
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

}//end main controller
