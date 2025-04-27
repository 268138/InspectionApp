package org.example.inspectionapplication.mapper;

import org.example.inspectionapplication.dto.vehicle.CreateVehicleRequest;
import org.example.inspectionapplication.dto.vehicle.UpdateVehicleRequest;
import org.example.inspectionapplication.dto.vehicle.VehicleResponse;
import org.example.inspectionapplication.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {
    Vehicle toEntity(CreateVehicleRequest dto);

    void updateEntityFromDto(UpdateVehicleRequest dto, @MappingTarget Vehicle entity);

    VehicleResponse toResponse(Vehicle entity);

    List<VehicleResponse> entityListToDto(List<Vehicle> entities);
}
