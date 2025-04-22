package org.example.inspectionapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.InspectionReport;
import org.example.inspectionapplication.service.InspectionReportService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class InspectionReportController {

    private final InspectionReportService reportService;

    @GetMapping
    public List<InspectionReport> getAll() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public InspectionReport getById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @GetMapping(params = "vehicleId")
    public List<InspectionReport> byVehicle(@RequestParam Long vehicleId) {
        return reportService.findReportsByVehicle(vehicleId);
    }

    @GetMapping(params = "centerId")
    public List<InspectionReport> byCenter(@RequestParam Long centerId) {
        return reportService.findReportsByCenter(centerId);
    }

    @GetMapping(path = "/range")
    public List<InspectionReport> byDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return reportService.findReportsByDateRange(from, to);
    }

    @PostMapping
    public InspectionReport create(@RequestBody InspectionReport report) {
        return reportService.createReport(report);
    }

    @PutMapping("/{id}")
    public InspectionReport update(@PathVariable Long id, @RequestBody InspectionReport report) {
        return reportService.updateReport(id, report);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reportService.deleteReport(id);
    }
}
