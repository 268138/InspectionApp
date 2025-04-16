package org.example.inspectionapplication.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String make;
    private String model;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerContactInfo;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<InspectionEvent> inspectionEvents = new ArrayList<>();

}
