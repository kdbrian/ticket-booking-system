package io.github.junrdev.bookingsys.controller.rest;

import io.github.junrdev.bookingsys.domain.dto.ScheduleDto;
import io.github.junrdev.bookingsys.model.Schedule;
import io.github.junrdev.bookingsys.service.ScheduleService;
import io.github.junrdev.bookingsys.util.mappers.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
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
    public ResponseEntity<Schedule> createOrUpdateSchedule(@RequestBody ScheduleDto dto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(dto));
    }

    // Retrieve a Schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable String id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    // Retrieve all Schedules
    @GetMapping("/")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    // Delete a Schedule by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteSchedule(@PathVariable String id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        if (schedule != null)
            scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
