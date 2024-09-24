package com.transport.drones.repository;

import com.transport.drones.model.Drone;
import org.springframework.data.repository.CrudRepository;


public interface DroneRepository extends CrudRepository<Drone, Integer> {
}