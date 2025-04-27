package org.example.inspectionapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.inspectionapplication.dto.report.CreateReportRequest;
import org.example.inspectionapplication.dto.report.ReportResponse;
import org.example.inspectionapplication.dto.report.UpdateReportRequest;
import org.example.inspectionapplication.entity.InspectionReport;
import org.example.inspectionapplication.mapper.InspectionReportMapper;
import org.example.inspectionapplication.service.InspectionReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Reports", description = "Endpoints for managing inspection reports")
public class InspectionReportController {

    private final InspectionReportService reportService;
    @Qualifier("inspectionReportMapperImpl")
    private final InspectionReportMapper reportMapper;

    @Operation(summary = "Add a new inspection report", description = "Creates a new inspection report and returns the created resource.")
    @ApiResponse(responseCode = "201", description = "Inspection report created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @PostMapping
    public ResponseEntity<ReportResponse> addReport(@Valid @RequestBody CreateReportRequest request) {
        log.info("Received request to add new inspection report: {}", request);
        InspectionReport saved = reportService.createReport(reportMapper.toEntity(request));
        ReportResponse response = reportMapper.toResponse(saved);
        log.info("Inspection report added successfully with ID: {}", saved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all inspection reports", description = "Retrieves a list of all inspection reports.")
    @ApiResponse(responseCode = "200", description = "List of inspection reports retrieved successfully")
    @ApiResponse(responseCode = "204", description = "No inspection reports found")
    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        log.info("Fetching all inspection reports");
        List<ReportResponse> reports = reportMapper.entityListToDto(reportService.getAllReports());
        if (reports.isEmpty()) {
            log.info("No inspection reports found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get an inspection report by ID", description = "Fetches a specific inspection report by ID.")
    @ApiResponse(responseCode = "200", description = "Inspection report retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Inspection report not found")
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        log.info("Fetching inspection report by ID {}", id);
        InspectionReport report = reportService.getReportById(id);
        ReportResponse response = reportMapper.toResponse(report);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get inspection reports by vehicle", description = "Retrieves inspection reports for a given vehicle ID.")
    @ApiResponse(responseCode = "200", description = "Inspection reports retrieved successfully")
    @GetMapping(params = "vehicleId")
    public ResponseEntity<List<ReportResponse>> getByVehicle(@RequestParam Long vehicleId) {
        log.info("Fetching inspection reports for vehicle ID {}", vehicleId);
        List<ReportResponse> reports = reportMapper.entityListToDto(reportService.findReportsByVehicle(vehicleId));
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get inspection reports by center", description = "Retrieves inspection reports for a given center ID.")
    @ApiResponse(responseCode = "200", description = "Inspection reports retrieved successfully")
    @GetMapping(params = "centerId")
    public ResponseEntity<List<ReportResponse>> getByCenter(@RequestParam Long centerId) {
        log.info("Fetching inspection reports for center ID {}", centerId);
        List<ReportResponse> reports = reportMapper.entityListToDto(reportService.findReportsByCenter(centerId));
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get inspection reports by date range", description = "Retrieves inspection reports between two dates.")
    @ApiResponse(responseCode = "200", description = "Inspection reports retrieved successfully")
    @GetMapping(path = "/range")
    public ResponseEntity<List<ReportResponse>> getByDateRange(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        log.info("Fetching inspection reports from {} to {}", from, to);
        List<ReportResponse> reports = reportMapper.entityListToDto(reportService.findReportsByDateRange(from, to));
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Update an inspection report", description = "Updates an existing inspection report by ID.")
    @ApiResponse(responseCode = "200", description = "Inspection report updated successfully")
    @ApiResponse(responseCode = "404", description = "Inspection report not found")
    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReportRequest request
    ) {
        log.info("Updating inspection report ID {} with data {}", id, request);
        InspectionReport existing = reportService.getReportById(id);
        reportMapper.updateEntityFromDto(request, existing);
        InspectionReport updated = reportService.updateReport(id, existing);
        ReportResponse response = reportMapper.toResponse(updated);
        log.info("Inspection report updated successfully ID {}: {}", id, response);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete an inspection report", description = "Deletes an inspection report by ID.")
    @ApiResponse(responseCode = "204", description = "Inspection report deleted successfully")
    @ApiResponse(responseCode = "404", description = "Inspection report not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        log.info("Deleting inspection report by ID {}", id);
        reportService.deleteReport(id);
        log.info("Inspection report deleted successfully ID {}", id);
        return ResponseEntity.noContent().build();
    }
}
