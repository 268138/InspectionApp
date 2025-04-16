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

    public List<InspectionCenter> getAllCenters(){
        return inspectionCenterRepository.findAll();
    }

}
