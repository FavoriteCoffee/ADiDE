package com.transport.drones.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.transport.drones.model.Drone;
import com.transport.drones.service.DroneService;

import java.util.List;

@RestController
@RequestMapping(value="/drones")
@Slf4j
public class DronesController {

    @Autowired
    private DroneService service;

    @GetMapping("/")
    public ResponseEntity<Object> getAllDrones() {
        try {
            List<Drone> drones = (List<Drone>) service.getAllDrones();
            return new ResponseEntity<Object>(drones, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDroneById(@PathVariable("id") Integer id) {
        try {
            Drone drone = service.getDrone(id);
            return new ResponseEntity<Object>(drone, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDroneById(@PathVariable("id") Integer id) {
        try {
            service.deleteDrone(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addDrone(@RequestBody Drone drone) {
        try {
            Drone savedDrone = service.addDrone(drone);
            return new ResponseEntity<Object>(savedDrone, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDrone(@RequestBody Drone drone, @PathVariable("id") Integer id) {
        try {
            Drone updatedDrone = service.updateDrone(id, drone);
            return new ResponseEntity<Object>(updatedDrone, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(value="/{engineType}/{droneNumber}")
//    public ResponseEntity<Drone> getDrones(
//            @PathVariable("droneId") String droneId,
//            @PathVariable("engineType") String engineType,
//            @PathVariable("droneNumber") int droneNumber) {
//
//        Drone drones = droneService.getDrones(droneId, engineType, droneNumber);
//        return ResponseEntity.ok(drones);
//    }
//
//    @PostMapping
//    public ResponseEntity<String> createDrones(
//            @PathVariable("droneId") String droneId,
//            @RequestBody Drone request) {
//        return ResponseEntity.ok(droneService.createDrones(request, droneId));
//    }

}
