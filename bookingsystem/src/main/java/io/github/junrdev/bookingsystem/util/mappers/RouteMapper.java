package io.github.junrdev.bookingsystem.util.mappers;

import io.github.junrdev.bookingsystem.dto.RouteDto;
import io.github.junrdev.bookingsystem.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {VehicleMappers.class}) // Use VehicleMapper for nested mappings
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

//    @Mapping(source = "schedule.id", target = "scheduleId")
    RouteDto routeToRouteDto(Route route);

//    @Mapping(source = "scheduleId", target = "schedule.id")
    Route routeDtoToRoute(RouteDto routeDto);
}
