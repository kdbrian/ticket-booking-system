package io.github.junrdev.bookingsystem.controller;

import io.github.junrdev.bookingsystem.model.Schedule;
import io.github.junrdev.bookingsystem.service.ScheduleService;
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

    @Autowired
    private ScheduleService scheduleService;

    // Create or Update a Schedule
    @PostMapping("/new")
    public ResponseEntity<Schedule> createOrUpdateSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    // Retrieve a Schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        return schedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Schedules
    @GetMapping("/")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    // Delete a Schedule by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        if (schedule.isPresent()) {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
