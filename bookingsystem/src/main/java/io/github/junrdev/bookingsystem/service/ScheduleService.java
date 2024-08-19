package io.github.junrdev.bookingsystem.service;

import io.github.junrdev.bookingsystem.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule saveSchedule(Schedule schedule);

    Optional<Schedule> getScheduleById(Long id);

    List<Schedule> getAllSchedules();

    void deleteSchedule(Long id);

    // Additional business methods can be defined here
}