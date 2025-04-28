package org.example.inspectionapplication.mapper;

import org.example.inspectionapplication.dto.report.CreateReportRequest;
import org.example.inspectionapplication.dto.report.ReportResponse;
import org.example.inspectionapplication.dto.report.UpdateReportRequest;
import org.example.inspectionapplication.entity.InspectionReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InspectionReportMapper {

    @Mapping(target = "vehicle.id",          source = "dto.vehicleId")
    @Mapping(target = "inspectionCenter.id", source = "dto.centerId")
    InspectionReport toEntity(CreateReportRequest dto);

    void updateEntityFromDto(UpdateReportRequest dto,
                             @MappingTarget InspectionReport entity);

    @Mapping(target = "vehicleId",           source = "entity.vehicle.id")
    @Mapping(target = "centerId",            source = "entity.inspectionCenter.id")
    ReportResponse toResponse(InspectionReport entity);

    List<ReportResponse> entityListToDto(List<InspectionReport> entities);
}
