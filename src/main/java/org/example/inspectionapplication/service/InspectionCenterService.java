package org.example.inspectionapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.inspectionapplication.entity.InspectionCenter;
import org.example.inspectionapplication.repository.InspectionCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InspectionCenterService {

    private final InspectionCenterRepository inspectionCenterRepository;

    public List<InspectionCenter> getAllCenters() {
        return inspectionCenterRepository.findAll();
    }

    public InspectionCenter getCenterById(Long id) {
        return inspectionCenterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inspection center not found with ID " + id));
    }

    public InspectionCenter updateCenter(Long id, InspectionCenter updatedCenter) {
        InspectionCenter existing = getCenterById(id);
        existing.setName(updatedCenter.getName());
        existing.setAddress(updatedCenter.getAddress());
        existing.setPhone(updatedCenter.getPhone());
        return inspectionCenterRepository.save(existing);
    }

    public void deleteCenter(Long id) {
        inspectionCenterRepository.deleteById(id);
    }

    public InspectionCenter createCenter(InspectionCenter center) {
        return inspectionCenterRepository.save(center);
    }

    public List<InspectionCenter> findByName(String name) {
        return inspectionCenterRepository.findByNameContainingIgnoreCase(name);
    }
}
