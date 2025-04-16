package org.example.inspectionapplication.service;


import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.InspectionReport;
import org.example.inspectionapplication.repository.InspectionReportRepository;
import org.example.inspectionapplication.repository.VehicleRepository;
import org.example.inspectionapplication.repository.InspectionCenterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionReportService {

    private final InspectionReportRepository inspectionReportRepository;
    private final VehicleRepository vehicleRepository;
    private final InspectionCenterRepository centerRepository;

    public List<InspectionReport> getAllEvents() {
        return inspectionReportRepository.findAll();
    }

    public InspectionReport getEventById(Long id) {
        return inspectionReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found: " + id));
    }

    public InspectionReport createReport(InspectionReport toCreateReport) {
        vehicleRepository.findById(toCreateReport.getVehicle().getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        centerRepository.findById(toCreateReport.getInspectionCenter().getId())
                .orElseThrow(() -> new RuntimeException("Center not found"));

        return inspectionReportRepository.save(toCreateReport);
    }

    public void deleteEvent(Long id) {
        inspectionReportRepository.deleteById(id);
    }

    public List<InspectionReport> findByVehicleId(Long vehicleId) {
        return inspectionReportRepository.findByVehicleId(vehicleId);
    }

    public List<InspectionReport> findByCenterId(Long centerId) {
        return inspectionReportRepository.findByInspectionCenterId(centerId);
    }

    public List<InspectionReport> findByDateRange(LocalDate from, LocalDate to) {
        return inspectionReportRepository.findByInspectionDateBetween(from, to);
    }

    public List<InspectionReport> findUpcoming() {
        return inspectionReportRepository.findByNextInspectionDateAfter(LocalDate.now());
    }
}
