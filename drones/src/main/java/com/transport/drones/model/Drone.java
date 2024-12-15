package com.transport.drones.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


/**
 Описание полей:
 id: Уникальный идентификатор дрона в бд.

 model: Модель дрона.

 serialNumber: Серийный номер дрона.

 weightLimit: Максимальный вес, который может поднять дрон.

 batteryCapacity: Емкость батареи дрона в процентах.

 state: Текущее состояние дрона ("IDLE", "LOADING", "DELIVERING", "RETURNING", "MAINTENANCE").
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Drone")
public class Drone extends RepresentationModel<Drone> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "weight_limit")
    private double weightLimit;

    @Column(name = "battery_capacity")
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;
}