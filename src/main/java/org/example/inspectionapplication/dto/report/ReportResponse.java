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
    private LocalDate nextInspectionDate;

    // ← these two lines were also missing!
    private Long vehicleId;
    private Long centerId;
}

