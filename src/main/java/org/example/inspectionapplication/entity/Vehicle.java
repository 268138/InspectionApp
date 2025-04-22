package org.example.inspectionapplication.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String model;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerEmail;
    private String yearOfManufacture;
    private String fuelType;
    private String co2Emission;
    private Long pollutionTax;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<InspectionReport> inspectionReports = new ArrayList<>();

}
