package com.transport.drones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Drones {
    private int id;
    private String hospitalName;
    private String boneType;
    private String segmentBone;
    private String fractureType;
    private boolean infection;
    private int patientsNumber;

}
