package io.github.junrdev.bookingsystem.repository;

import io.github.junrdev.bookingsystem.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}