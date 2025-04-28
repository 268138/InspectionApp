package org.example.inspectionapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.Vehicle;
import org.example.inspectionapplication.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID " + id));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle newVehicleData) {
        Vehicle existingVehicle = getVehicleById(id);
        existingVehicle.setLicensePlate(newVehicleData.getLicensePlate());
        existingVehicle.setModel(newVehicleData.getModel());
        existingVehicle.setOwnerFirstName(newVehicleData.getOwnerFirstName());
        existingVehicle.setOwnerLastName(newVehicleData.getOwnerLastName());
        existingVehicle.setOwnerEmail(newVehicleData.getOwnerEmail());

        return vehicleRepository.save(existingVehicle);
    }

    public List<Vehicle> findByLicensePlate(String plate) {
        return vehicleRepository.findByLicensePlateContainingIgnoreCase(plate);
    }
}
