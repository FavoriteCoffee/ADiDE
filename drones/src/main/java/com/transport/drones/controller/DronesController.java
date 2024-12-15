package com.transport.drones.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import com.transport.drones.model.Drone;
import com.transport.drones.service.DroneService;

import java.util.List;

@RestController
@RequestMapping(value="/drones")
@Slf4j
public class DronesController {

    @Autowired
    private DroneService service;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
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
    public ResponseEntity<Object> getDroneById(@PathVariable("id") Integer id, HttpServletRequest request) {
        try {
            Drone drone = service.getDrone(id, request);
            return new ResponseEntity<Object>(drone, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDroneById(@PathVariable("id") Integer id, HttpServletRequest request) {
        try {
            Drone drone = service.getDrone(id, request);
            service.deleteDrone(id);
            log.info(messageSource.getMessage("drones.delete.message", new Object[]{drone.getSerialNumber(), drone.getId()}, localeResolver.resolveLocale(request)));
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addDrone(@RequestBody Drone drone, HttpServletRequest request) {
        try {
            Drone savedDrone = service.addDrone(drone);
            log.info(messageSource.getMessage("drones.create.message", new Object[]{savedDrone.getSerialNumber(), savedDrone.getId()}, localeResolver.resolveLocale(request)));
            return new ResponseEntity<Object>(savedDrone, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDrone(@RequestBody Drone drone, @PathVariable("id") Integer id, HttpServletRequest request) {
        try {
            Drone updatedDrone = service.updateDrone(id, drone);
            log.info(messageSource.getMessage("drones.update.message", new Object[]{updatedDrone.getSerialNumber(), updatedDrone.getId()}, localeResolver.resolveLocale(request)));
            return new ResponseEntity<Object>(updatedDrone, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
