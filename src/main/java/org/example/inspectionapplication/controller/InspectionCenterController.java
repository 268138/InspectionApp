package org.example.inspectionapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.dto.center.CenterResponse;
import org.example.inspectionapplication.dto.center.CreateCenterRequest;
import org.example.inspectionapplication.dto.center.UpdateCenterRequest;
import org.example.inspectionapplication.mapper.InspectionCenterMapper;
import org.example.inspectionapplication.service.InspectionCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
@Tag(name="Inspection Centers", description="Endpoints for managing inspection centers")
public class InspectionCenterController {

    private final InspectionCenterService centerService;
    private final InspectionCenterMapper centerMapper;

    @Operation(summary="List all inspection centers")
    @ApiResponse(responseCode="200", description="Centers retrieved")
    @ApiResponse(responseCode="204", description="No centers found")
    @GetMapping
    public ResponseEntity<List<CenterResponse>> getAll() {
        var dtos = centerMapper.entityListToDto(centerService.getAllCenters());
        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary="Get an inspection center by ID")
    @ApiResponse(responseCode="200", description="Center found")
    @ApiResponse(responseCode="404", description="Center not found")
    @GetMapping("/{id}")
    public ResponseEntity<CenterResponse> getById(@PathVariable Long id) {
        var center = centerService.getCenterById(id);
        return ResponseEntity.ok(centerMapper.toResponse(center));
    }

    @Operation(summary="Search centers by name")
    @ApiResponse(responseCode="200", description="Matches found")
    @ApiResponse(responseCode="204", description="No matches")
    @GetMapping("/search")
    public ResponseEntity<List<CenterResponse>> searchByName(
            @RequestParam String name
    ) {
        var dtos = centerMapper.entityListToDto(centerService.findByName(name));
        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary="Create a new inspection center")
    @ApiResponse(responseCode="201", description="Center created")
    @ApiResponse(responseCode="400", description="Invalid input")
    @PostMapping
    public ResponseEntity<CenterResponse> create(
            @Valid @RequestBody CreateCenterRequest rq
    ) {
        var saved = centerService.createCenter(centerMapper.toEntity(rq));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(centerMapper.toResponse(saved));
    }

    @Operation(summary="Update an existing inspection center")
    @ApiResponse(responseCode="200", description="Center updated")
    @ApiResponse(responseCode="404", description="Center not found")
    @PutMapping("/{id}")
    public ResponseEntity<CenterResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCenterRequest rq
    ) {
        var existing = centerService.getCenterById(id);
        centerMapper.updateEntityFromDto(rq, existing);
        var updated = centerService.updateCenter(id, existing);
        return ResponseEntity.ok(centerMapper.toResponse(updated));
    }

    @Operation(summary="Delete an inspection center")
    @ApiResponse(responseCode="204", description="Center deleted")
    @ApiResponse(responseCode="404", description="Center not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
