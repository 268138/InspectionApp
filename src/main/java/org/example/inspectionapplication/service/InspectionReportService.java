package org.example.inspectionapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.InspectionReport;
import org.example.inspectionapplication.repository.InspectionReportRepository;
import org.example.inspectionapplication.repository.VehicleRepository;
import org.example.inspectionapplication.repository.InspectionCenterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionReportService {

    private final InspectionReportRepository reportRepository;
    private final VehicleRepository vehicleRepository;
    private final InspectionCenterRepository centerRepository;

    public List<InspectionReport> getAllReports() {
        return reportRepository.findAll();
    }

    public InspectionReport getReportById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found: " + id));
    }

    @Transactional
    public InspectionReport createReport(InspectionReport toCreate) {

        vehicleRepository.findById(toCreate.getVehicle().getId()).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        centerRepository.findById(toCreate.getInspectionCenter().getId()).orElseThrow(() -> new RuntimeException("Center not found"));

        return reportRepository.save(toCreate);
    }

    @Transactional
    public InspectionReport updateReport(Long id, InspectionReport updated) {
        InspectionReport existing = getReportById(id);

        existing.setInspectionDate(updated.getInspectionDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setInspectorName(updated.getInspectorName());
        existing.setInspectionResult(updated.getInspectionResult());
        existing.setNextInspectionDate(updated.getNextInspectionDate());

        existing.setVehicle(updated.getVehicle());
        existing.setInspectionCenter(updated.getInspectionCenter());

        return reportRepository.save(existing);
    }

    @Transactional
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public List<InspectionReport> findByCenterId(Long centerId) {
        return reportRepository.findByInspectionCenterId(centerId);
    }

    public List<InspectionReport> findReportsByVehicle(Long vehicleId) {
        return reportRepository.findByVehicleId(vehicleId);
    }

    public List<InspectionReport> findReportsByCenter(Long centerId) {
        return reportRepository.findByInspectionCenterId(centerId);
    }

    public List<InspectionReport> findReportsByDateRange(LocalDate from, LocalDate to) {
        return reportRepository.findByInspectionDateBetween(from, to);
    }
}
