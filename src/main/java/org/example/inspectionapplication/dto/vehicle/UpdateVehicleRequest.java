package org.example.inspectionapplication.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateVehicleRequest {

    @NotBlank
    private String licensePlate;

    @NotBlank
    private String ownerFirstName;

    @NotBlank
    private String ownerLastName;

    @NotBlank
    private String ownerEmail;

    @NotNull
    @Positive
    private Integer co2Emission;

    @NotNull
    @Positive
    private Long pollutionTax;
}
