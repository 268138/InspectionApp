package org.example.inspectionapplication.repository;

import org.example.inspectionapplication.entity.InspectionCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionCenterRepository extends JpaRepository<InspectionCenter, Long > {
}
