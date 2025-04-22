package org.example.inspectionapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.InspectionCenter;
import org.example.inspectionapplication.service.InspectionCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class InspectionCenterController {

    private final InspectionCenterService centerService;

    @GetMapping
    public List<InspectionCenter> getAll() {
        return centerService.getAllCenters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspectionCenter> getById(@PathVariable Long id) {
        InspectionCenter center = centerService.getCenterById(id);
        return ResponseEntity.ok(center);
    }

    @GetMapping("/search")
    public List<InspectionCenter> searchByName(@RequestParam String name) {
        return centerService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<InspectionCenter> create(@RequestBody InspectionCenter center) {
        InspectionCenter saved = centerService.createCenter(center);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public InspectionCenter update(@PathVariable Long id, @RequestBody InspectionCenter center) {
        return centerService.updateCenter(id, center);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
