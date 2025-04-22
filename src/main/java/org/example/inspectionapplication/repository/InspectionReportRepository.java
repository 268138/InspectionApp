package org.example.inspectionapplication.repository;

import org.example.inspectionapplication.entity.InspectionReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InspectionReportRepository extends JpaRepository<InspectionReport, Long> {
    List<InspectionReport> findByVehicleId(Long vehicleId);

    List<InspectionReport> findByInspectionCenterId(Long centerId);

    List<InspectionReport> findByNextInspectionDateAfter(LocalDate now);

    List<InspectionReport> findByInspectionDateBetween(LocalDate from, LocalDate to);


}
