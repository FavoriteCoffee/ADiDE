package com.transport.drones.service;

import java.util.List;
import java.util.Optional;

import com.transport.drones.controller.DronesController;
import com.transport.drones.model.DroneState;
import com.transport.drones.repository.DroneRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.transport.drones.model.Drone;
import org.springframework.web.servlet.LocaleResolver;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneService {
    private final DroneRepository repository;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public Iterable<Drone> getAllDrones(){
        return repository.findAll();
    }
    public Drone getDrone(int id, HttpServletRequest request){
        Optional<Drone> result =  repository.findById(id);
        if (result.isEmpty()) return null;
        Drone drone = result.get();

        Drone updatedDrone = new Drone();
        updatedDrone.setId(drone.getId());
        updatedDrone.setModel(drone.getModel());
        updatedDrone.setSerialNumber(drone.getSerialNumber());
        updatedDrone.setWeightLimit(drone.getWeightLimit());
        updatedDrone.setBatteryCapacity(drone.getBatteryCapacity());
        updatedDrone.setState(drone.getState());

        String droneStateHeader = request.getHeader("DroneState");
        if (droneStateHeader != null) {
            try {
                DroneState newState = DroneState.valueOf(droneStateHeader);
                updatedDrone.setState(newState);
            } catch (IllegalArgumentException ex) {
                log.warn("Invalid DroneState header value: {}", droneStateHeader);
            }
        }

        drone.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DronesController.class).getDroneById(id, request)).withSelfRel());
        drone.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DronesController.class).addDrone(drone, null)).withRel(messageSource.getMessage("hateoas.create.link_name", new Object[]{}, localeResolver.resolveLocale(request)))));
        drone.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DronesController.class).updateDrone(updatedDrone, id, null)).withRel(messageSource.getMessage("hateoas.update.link_name", new Object[]{}, localeResolver.resolveLocale(request)))));
        drone.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DronesController.class).deleteDroneById(id, null)).withRel(messageSource.getMessage("hateoas.delete.link_name", new Object[]{}, localeResolver.resolveLocale(request)))));
        return drone;
    }

    public Drone addDrone(Drone drone){
        return repository.save(drone);
    }

    public Drone updateDrone(int id, Drone drone){
        if (repository.findById(id).isEmpty()) throw new IllegalArgumentException("No drones found with provided id");
        drone.setId(id);
        return repository.save(drone);
    }

    public void deleteDrone(int id){
        repository.deleteById(id);
    }

}
