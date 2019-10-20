package com.thoughtworks.parking_lot.parkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping(produces = {"application/json"})
    public ParkingLot add(@RequestBody ParkingLot parkingLot){
        return parkingLotService.save(parkingLot);
    }
//    AC1: If I buy a new parking lot,
//    I can add a parking lot on the system interface. The information I need to input include:
//    name(unique)
//    capacity(cannot be minus)
//    location


}
