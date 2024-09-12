package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {VehicleMappers.class})
public interface RouteMapper {

    @Mapping(source = "schedule.id", target = "scheduleId")
    RouteDto routeToRouteDto(Route route);

    @Mapping(source = "scheduleId", target = "schedule.id")
    Route routeDtoToRoute(RouteDto routeDto);
}
