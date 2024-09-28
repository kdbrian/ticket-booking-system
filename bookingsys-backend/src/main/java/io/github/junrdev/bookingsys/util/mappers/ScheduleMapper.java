package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.domain.dto.ScheduleDto;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleMappers.class, RouteMapper.class})
public interface ScheduleMapper {

    @Mapping(source = "company.id", target = "companyId")
    ScheduleDto scheduleToScheduleDto(Schedule schedule);

//    @Mapping(source = "id", target = "scheduleId")
    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);

}
