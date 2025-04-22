package org.example.inspectionapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.Vehicle;
import org.example.inspectionapplication.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> all() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle one(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicleDto) {
        return vehicleService.createVehicle(vehicleDto);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id,
                          @RequestBody Vehicle vehicleDto) {
        return vehicleService.updateVehicle(id, vehicleDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
