package com.thoughtworks.parking_lot.parkingLot;

import com.thoughtworks.parking_lot.car.Car;
import com.thoughtworks.parking_lot.order.Orders;
import com.thoughtworks.parking_lot.order.OrderService;
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
    public HttpEntity<ParkingLot> add(@RequestBody ParkingLot parkingLot){
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
    public ResponseEntity<Optional<ParkingLot>>
        listSingleParkingLot(@PathVariable String name) throws NotFoundException {
            Optional<ParkingLot> foundParkingLot = parkingLotService.findByName(name);
            if(foundParkingLot.isPresent()){
                return new ResponseEntity<Optional<ParkingLot>>(foundParkingLot, HttpStatus.OK);
            }
            throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

    @PatchMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<ParkingLot>>
            updateParkingLot(@PathVariable String name, @RequestBody ParkingLot parkingLot) throws NotFoundException {
                Optional<ParkingLot> searchParkingLot = parkingLotService.findByName(name);
                if(searchParkingLot.isPresent()){
                    ParkingLot foundParkingLot = searchParkingLot.get();
                    foundParkingLot.setCapacity(parkingLot.getCapacity());
                    parkingLotService.save(foundParkingLot);
                    return new ResponseEntity<Optional<ParkingLot>>(Optional.of(foundParkingLot), HttpStatus.OK);
                }
                throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

    @DeleteMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable String name) throws NotFoundException {
        boolean wasDeleted = parkingLotService.delete(name);
        if(wasDeleted){
            return new ResponseEntity<>("Parking lot `" + name + "` deleted successfully!", HttpStatus.OK);
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

    @PostMapping(value = "/{name}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Orders>>
        createOrder(@PathVariable String name, @RequestBody Car car) throws NotFoundException {
        Optional<ParkingLot> searchParking = parkingLotService.findByName(name);
            if(searchParking.isPresent()){
                Orders newOrders = new Orders();
                newOrders.setParkingLotName(name);
                newOrders.setPlateNumber(car.getPlateNumber());
                newOrders.setCreationTime(new Timestamp(new Date().getTime()));
                newOrders.setCloseTime(null);
                newOrders.setOrderStatus("Open");
                orderService.save(newOrders);
                return new ResponseEntity<Optional<Orders>>(Optional.of(newOrders), HttpStatus.OK);
            }
            throw new NotFoundException("Parking lot " + name + " is full!");

    }

}
