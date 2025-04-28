package org.example.inspectionapplication.dto.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class UpdateReportRequest {

    @NotNull
    private LocalDate inspectionDate;

    @NotNull
    private LocalDate expiryDate;

    @NotBlank
    private String inspectorName;

    @NotBlank
    private String result;

    private String notes;

    private LocalDate nextInspectionDate;

}
