package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleMappers.class})
public interface RouteMapper {

    @Mapping(source = "schedule.id", target = "scheduleId")
    @Mapping(source = "county.countyName", target = "countyName")  // Fix field reference
    @Mapping(source = "subCounty.subCountyName", target = "subCounty")  // Fix field reference
    RouteDto routeToRouteDto(Route route);

    @Mapping(source = "scheduleId", target = "schedule.id")
    @Mapping(source = "countyName", target = "county.countyName")  // Reverse mapping for county name
    @Mapping(source = "subCountyName", target = "subCounty.subCountyName")  // Reverse mapping for sub-county name
    Route routeDtoToRoute(RouteDto routeDto);
}

