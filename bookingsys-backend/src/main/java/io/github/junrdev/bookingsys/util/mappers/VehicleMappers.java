package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.VehicleDto;
import io.github.junrdev.bookingsys.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMappers {

    // Map entity to DTO
    @Mapping(source = "route.id", target = "routeId") // Map Route's routeId to VehicleDto's routeId
    VehicleDto toDto(Vehicle vehicle);

    @Mapping(source = "routeId", target = "route.id") // Map VehicleDto's routeId to Route's routeId
    Vehicle toEntity(VehicleDto vehicleDto);
}
