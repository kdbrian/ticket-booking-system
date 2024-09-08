package io.github.junrdev.bookingsystem.repository;

import io.github.junrdev.bookingsystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository   extends JpaRepository<Vehicle, Long> {
}
