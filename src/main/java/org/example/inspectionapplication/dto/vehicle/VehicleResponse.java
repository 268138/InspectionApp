package org.example.inspectionapplication.dto.vehicle;


import lombok.Data;

@Data
public class VehicleResponse {

    private Long id;
    private String licensePlate;
    private String model;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerEmail;
    private Integer yearOfManufacture;
    private String fuelType;
    private Integer co2Emission;
    private Long pollutionTax;
}
