package org.example.inspectionapplication.repository;

import org.example.inspectionapplication.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository <Vehicle, Long> {

}