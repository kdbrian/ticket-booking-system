package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.ScheduleDto;
import io.github.junrdev.bookingsys.model.Schedule;
import io.github.junrdev.bookingsys.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ScheduleGraphController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleGraphController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @QueryMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @QueryMapping
    public Schedule getScheduleById(@Argument String id) {
        return scheduleService.getScheduleById(id);
    }

    @MutationMapping
    public Schedule saveSchedule(@Argument ScheduleDto dto) {
        return scheduleService.saveSchedule(dto);
    }

    @MutationMapping
    public Boolean deleteSchedule(@Argument String id) {
        return scheduleService.deleteSchedule(id);
    }

}
