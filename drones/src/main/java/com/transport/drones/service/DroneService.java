package com.transport.drones.service;

import java.util.List;
import java.util.Optional;

import com.transport.drones.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.transport.drones.model.Drone;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository repository;

    public Iterable<Drone> getAllDrones(){
        return repository.findAll();
    }
    public Drone getDrone(int id){
        Optional<Drone> result =  repository.findById(id);
        return result.orElse(null);
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




