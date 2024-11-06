package com.hospitals.fractures.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class Fractures extends RepresentationModel<Fractures> {

    private int id;
    private String hospitalName;
    private String boneType;
    private String segmentBone;
    private String fractureType;
    private boolean infection;
    private int patientsNumber;

}
