package org.example.inspectionapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.dto.vehicle.CreateVehicleRequest;
import org.example.inspectionapplication.dto.vehicle.UpdateVehicleRequest;
import org.example.inspectionapplication.dto.vehicle.VehicleResponse;
import org.example.inspectionapplication.mapper.VehicleMapper;
import org.example.inspectionapplication.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor

@Tag(name = "Vehicles", description = "Endpoints for managing vehicles")
public class VehicleController {
    private final VehicleService service;
    private final VehicleMapper mapper;

    @Operation(summary = "Get all vehicles")
    @ApiResponse(responseCode = "200", description = "List of vehicles")
    @ApiResponse(responseCode = "204", description = "No vehicles found")
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> all() {
        var dtos = mapper.entityListToDto(service.getAllVehicles());
        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Get a vehicle by ID")
    @ApiResponse(responseCode = "200", description = "Vehicle found")
    @ApiResponse(responseCode = "404", description = "Vehicle not found")
    @GetMapping("/{id}")
    public VehicleResponse one(@PathVariable Long id) {
        return mapper.toResponse(service.getVehicleById(id));
    }

    @Operation(summary = "Create a new vehicle")
    @ApiResponse(responseCode = "201", description = "Vehicle created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<VehicleResponse> create(
            @Valid @RequestBody CreateVehicleRequest rq
    ) {
        var saved = service.createVehicle(mapper.toEntity(rq));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @Operation(summary = "Update an existing vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle updated")
    @ApiResponse(responseCode = "404", description = "Vehicle not found")
    @PutMapping("/{id}")
    public VehicleResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateVehicleRequest rq
    ) {
        var existing = service.getVehicleById(id);
        mapper.updateEntityFromDto(rq, existing);
        var updated = service.updateVehicle(id, existing);
        return mapper.toResponse(updated);
    }

    @Operation(summary = "Delete a vehicle")
    @ApiResponse(responseCode = "204", description = "Vehicle deleted")
    @ApiResponse(responseCode = "404", description = "Vehicle not found")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteVehicle(id);
    }

    //
    // Filtering endpoints:
    //
    @Operation(summary = "Search vehicles by license plate")
    @GetMapping(params = "licensePlate")
    public ResponseEntity<List<VehicleResponse>> filterByPlate(
            @RequestParam String licensePlate
    ) {
        var dtos = mapper.entityListToDto(
                service.findByLicensePlate(licensePlate)
        );
        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtos);
    }
}
