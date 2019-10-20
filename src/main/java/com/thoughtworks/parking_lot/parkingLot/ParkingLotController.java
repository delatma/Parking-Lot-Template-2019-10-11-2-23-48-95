package com.thoughtworks.parking_lot.parkingLot;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping(produces = {"application/json"})
    public HttpEntity<ParkingLot> add(@RequestBody ParkingLot parkingLot){
        return new ResponseEntity<>(parkingLotService.save(parkingLot), HttpStatus.CREATED);
    }

    @RequestMapping(produces = {"application/json"})
    public Iterable<ParkingLot> listAllParkingLots(@RequestBody ParkingLot parkingLot){
        return parkingLotService.viewAllParkingLots(parkingLot);
    }

    @DeleteMapping(produces = {"application/json"})
    @RequestMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable String name) throws NotFoundException {
        boolean wasDeleted = parkingLotService.delete(name);
        if(wasDeleted){
            return new ResponseEntity<>("Parking lot `" + name + "` deleted successfully!", HttpStatus.OK);
        }
        throw new NotFoundException("Parking lot " + name + " does not exist!");
    }

}
