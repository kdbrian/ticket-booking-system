package io.github.junrdev.bookingsystem.util.mappers;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMappers {

    VehicleMappers INSTANCE = Mappers.getMapper(VehicleMappers.class);

    // Map entity to DTO
    @Mapping(source = "route.id", target = "routeId")
    VehicleDto toDto(Vehicle vehicle);

    // Map DTO to entity
    @Mapping(source = "routeId", target = "route.id")
    Vehicle toEntity(VehicleDto vehicleDto);
}
