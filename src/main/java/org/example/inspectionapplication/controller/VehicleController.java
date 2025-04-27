package org.example.inspectionapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.dto.vehicle.CreateVehicleRequest;
import org.example.inspectionapplication.dto.vehicle.UpdateVehicleRequest;
import org.example.inspectionapplication.dto.vehicle.VehicleResponse;
import org.example.inspectionapplication.mapper.VehicleMapper;
import org.example.inspectionapplication.service.VehicleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService service;
    @Qualifier("vehicleMapper")
    private final VehicleMapper mapper;

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> all() {
        var dtos = mapper.entityListToDto(service.getAllVehicles());
        if (dtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public VehicleResponse one(@PathVariable Long id) {
        return mapper.toResponse(service.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> create(
            @Valid @RequestBody CreateVehicleRequest rq
    ) {
        var saved = service.createVehicle(mapper.toEntity(rq));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteVehicle(id);
    }
}
