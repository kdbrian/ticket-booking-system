package io.github.junrdev.bookingsystem.util.mappers;

import io.github.junrdev.bookingsystem.dto.RouteDto;
import io.github.junrdev.bookingsystem.dto.ScheduleDto;
import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "company.id", target = "companyId")
    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    @Mapping(source = "companyId", target = "company.id")
    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);

    @Mapping(source = "schedule.id", target = "scheduleId")
    RouteDto routeToRouteDto(Route route);

    @Mapping(source = "scheduleId", target = "schedule.id")
    Route routeDtoToRoute(RouteDto routeDto);
}
