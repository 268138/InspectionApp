package org.example.inspectionapplication.dto.report;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportResponse {
    private Long id;
    private LocalDate inspectionDate;
    private LocalDate expiryDate;
    private String inspectorName;
    private String result;
    private String notes;
    private LocalDate nextInspectionDate;

    private Long vehicleId;
    private Long centerId;
}

