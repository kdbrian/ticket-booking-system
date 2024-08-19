package io.github.junrdev.bookingsystem.controller;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.model.Vehicle;
import io.github.junrdev.bookingsystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Create or Update a Vehicle
    @PostMapping("/new")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody VehicleDto dto) {
        Vehicle savedVehicle = vehicleService.saveVehicle(dto);
        return ResponseEntity.ok(savedVehicle);
    }

    // Retrieve a Vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Vehicles
    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Delete a Vehicle by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
