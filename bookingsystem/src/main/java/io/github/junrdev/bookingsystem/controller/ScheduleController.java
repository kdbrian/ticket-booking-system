package io.github.junrdev.bookingsystem.controller;

import io.github.junrdev.bookingsystem.dto.ScheduleDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Schedule;
import io.github.junrdev.bookingsystem.service.ScheduleService;
import io.github.junrdev.bookingsystem.util.mappers.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    //left here at(19/8/24 3AM to sleep) (test this, then route, and so on)
    //dependency flow
    // company -> schedule exists -> route exists -> vehicle exists -> booking

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }
    // Create or Update a Schedule

    @PostMapping("/new")
    public ResponseEntity<ScheduleDto> createOrUpdateSchedule(@RequestBody ScheduleDto dto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(dto));
    }

    // Retrieve a Schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> optschedule = scheduleService.getScheduleById(id);
        return optschedule.map(schedule -> ResponseEntity.ok(scheduleMapper.scheduleToScheduleDto(schedule)))
                .orElseThrow(() -> new NotFoundException("Failed to fetch schedule with id " + id));
    }

    // Retrieve all Schedules
    @GetMapping("/")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules.stream().map(scheduleMapper::scheduleToScheduleDto).toList());
    }

    // Delete a Schedule by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        if (schedule.isPresent()) {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new NotFoundException("Failed to fetch schedule with id " + id);
        }
    }
}
