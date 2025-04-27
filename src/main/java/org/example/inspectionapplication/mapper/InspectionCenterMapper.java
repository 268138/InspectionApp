package org.example.inspectionapplication.mapper;

import org.example.inspectionapplication.dto.center.CenterResponse;
import org.example.inspectionapplication.dto.center.CreateCenterRequest;
import org.example.inspectionapplication.dto.center.UpdateCenterRequest;
import org.example.inspectionapplication.entity.InspectionCenter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InspectionCenterMapper {
    InspectionCenter toEntity(CreateCenterRequest dto);
    void updateEntityFromDto(UpdateCenterRequest dto, @MappingTarget InspectionCenter entity);
    CenterResponse toResponse(InspectionCenter entity);
}