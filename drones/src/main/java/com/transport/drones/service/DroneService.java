package com.transport.drones.service;

import java.util.Random;
import org.springframework.stereotype.Service;

import com.transport.drones.model.Drones;

@Service
public class DroneService {

    public Drones getDrones(String hospitalName, String boneType, int patientsNumber) {
        Drones drones = new Drones();
        drones.setId(new Random().nextInt(1000));
        drones.setHospitalName(hospitalName);
        drones.setBoneType(boneType);
        drones.setSegmentBone("diaphyseal");
        drones.setFractureType("closed");
        drones.setInfection(false);
        drones.setPatientsNumber(patientsNumber);

        return drones;

    }

    public String createDrones(Drones drones, String hospitalName){
        String responseMessage = null;
        if(drones != null) {
            drones.setHospitalName(hospitalName);
            responseMessage = String.format("This is the post and the object is: %s", drones.toString());
        }

        return responseMessage;
    }

}


