package com.transport.drones.service;

import java.util.List;
import java.util.Optional;

import com.transport.drones.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
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


//    public Drone getDrones(String hospitalName, String boneType, int patientsNumber) {
//        Drone drones = new Drone();
//        drones.setId(new Random().nextInt(1000));
//        drones.setHospitalName(hospitalName);
//        drones.setBoneType(boneType);
//        drones.setSegmentBone("diaphyseal");
//        drones.setFractureType("closed");
//        drones.setInfection(false);
//        drones.setPatientsNumber(patientsNumber);
//
//        return drones;
//
//    }
//
//    public String createDrones(Drone drones, String hospitalName){
//        String responseMessage = null;
//        if(drones != null) {
//            drones.setHospitalName(hospitalName);
//            responseMessage = String.format("This is the post and the object is: %s", drones.toString());
//        }
//
//        return responseMessage;
//    }

}

@Service
public class FracturesService {

    @Autowired
    MessageSource messages;


    public String createFractures(Fractures fractures, String hospitalName, Locale locale) {
        String responseMessage = null;
        if(fractures != null) {
            fractures.setHospitalName(hospitalName);
            responseMessage = String.format(messages.getMessage("fractures.create.message", null,locale), fractures.toString());
        }

        return responseMessage;
    }
}




