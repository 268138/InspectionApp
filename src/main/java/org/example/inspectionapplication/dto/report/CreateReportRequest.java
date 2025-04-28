package org.example.inspectionapplication.dto.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReportRequest {

    @NotNull
    private LocalDate inspectionDate;

    @NotNull
    private LocalDate expiryDate;

    @NotBlank
    private String inspectorName;

    @NotBlank
    private String result;

    @NotBlank
    private String notes;

    private LocalDate nextInspectionDate;

    @NotNull
    private Long vehicleId;

    @NotNull
    private Long centerId;
}
