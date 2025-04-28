package org.example.inspectionapplication.repository;

import org.example.inspectionapplication.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VehicleRepository extends JpaRepository <Vehicle, Long> {

    List<Vehicle> findByLicensePlateContainingIgnoreCase(String plate);
}