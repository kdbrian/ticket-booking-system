package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.domain.dto.VehicleDto;
import io.github.junrdev.bookingsys.model.Vehicle;

import java.util.List;

public interface VehicleService {

    // Create or Update a Vehicle
    Vehicle saveVehicle(VehicleDto dto);

    // Retrieve a Vehicle by ID
    Vehicle getVehicleById(String id);

    // Retrieve all Vehicles
    List<Vehicle> getAllVehicles();

    // Delete a Vehicle by ID
    boolean deleteVehicle(String id);

    Vehicle updateVehicle(String id, VehicleDto dto);

    List<Vehicle> getVehiclesByRoute(String routeId);
}
