package org.example.inspectionapplication.repository;

import org.example.inspectionapplication.entity.InspectionCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InspectionCenterRepository extends JpaRepository<InspectionCenter, Long > {

    List<InspectionCenter> findByNameContainingIgnoreCase(String name);
}
