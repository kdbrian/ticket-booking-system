package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleMappers.class})
public interface RouteMapper {

    @Mapping(source = "schedule.id", target = "scheduleId")

    //from unified
    @Mapping(source = "fromLocationCountySubCounty.county.countyName", target = "fromCountyName")
    @Mapping(source = "fromLocationCountySubCounty.subCounty.subCountyName", target = "fromSubCountyName")

    //to unified
    @Mapping(source = "toLocationCountySubCounty.county.countyName", target = "toCountyName")
    @Mapping(source = "toLocationCountySubCounty.subCounty.subCountyName", target = "toSubCountyName")
    RouteDto routeToRouteDto(Route route);

    @Mapping(source = "scheduleId", target = "schedule.id")

    //from
    @Mapping(source = "fromCountyName", target = "fromLocationCountySubCounty.county.countyName")
    @Mapping(source = "fromSubCountyName", target = "fromLocationCountySubCounty.subCounty.subCountyName")

    //to
    @Mapping(source = "toCountyName", target = "toLocationCountySubCounty.county.countyName")
    @Mapping(source = "toSubCountyName", target = "toLocationCountySubCounty.subCounty.subCountyName")
    Route routeDtoToRoute(RouteDto routeDto);
}

