    package io.github.junrdev.bookingsystem.util.mappers;

    import io.github.junrdev.bookingsystem.dto.VehicleDto;
    import io.github.junrdev.bookingsystem.model.Vehicle;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;

    @Mapper(componentModel = "spring")
    public interface VehicleMappers {
        // Map entity to DTO
//        @Mapping(source = "route.routeId", target = "routeId")
        VehicleDto toDto(Vehicle vehicle);

        // Map DTO to entity
//        @Mapping(source = "routeId", target = "route.routeId")
        Vehicle toEntity(VehicleDto vehicleDto);
    }
