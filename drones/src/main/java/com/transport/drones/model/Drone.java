package com.transport.drones.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Card")
public class Drone {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "hospitalName")
    private String hospitalName;
    @Column(name = "boneType")
    private String boneType;
    @Column(name = "segmentBone")
    private String segmentBone;
    @Column(name = "fractureType")
    private String fractureType;
    @Column(name = "infection")
    private boolean infection;
    @Column(name = "patientsNumber")
    private int patientsNumber;
}
