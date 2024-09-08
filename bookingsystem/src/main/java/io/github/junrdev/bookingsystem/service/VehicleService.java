package io.github.junrdev.bookingsystem.service;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.model.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    // Create or Update a Vehicle
    @Transactional
    Vehicle saveVehicle(VehicleDto dto);

    // Retrieve a Vehicle by ID
    Optional<Vehicle> getVehicleById(Long id);

    // Retrieve all Vehicles
    List<Vehicle> getAllVehicles();

    // Delete a Vehicle by ID
    @Transactional
    void deleteVehicle(Long id);

    VehicleDto updateVehicle(Long id, VehicleDto dto);
}
