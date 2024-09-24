package com.transport.drones.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.transport.drones.model.Drones;
import com.transport.drones.service.DroneService;

@RestController
@RequestMapping(value="drones/{droneId}")
public class dronesController {

    @Autowired
    private DroneService droneService;

    @GetMapping(value="/{engineType}/{droneNumber}")
    public ResponseEntity<Drones> getDrones(
            @PathVariable("droneId") String droneId,
            @PathVariable("engineType") String engineType,
            @PathVariable("droneNumber") int droneNumber) {

        Drones drones = droneService.getDrones(droneId, engineType, droneNumber);
        return ResponseEntity.ok(drones);
    }

    @PostMapping
    public ResponseEntity<String> createDrones(
            @PathVariable("droneId") String droneId,
            @RequestBody Drones request) {
        return ResponseEntity.ok(droneService.createDrones(request, droneId));
    }

}
