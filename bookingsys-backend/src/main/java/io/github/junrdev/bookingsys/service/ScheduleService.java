package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.domain.dto.ScheduleDto;
import io.github.junrdev.bookingsys.model.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule saveSchedule(ScheduleDto dto);

    Schedule getScheduleById(String id);

    List<Schedule> getAllSchedules();

    boolean deleteSchedule(String id);

    // Additional business methods can be defined here
}