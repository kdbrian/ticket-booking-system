package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}