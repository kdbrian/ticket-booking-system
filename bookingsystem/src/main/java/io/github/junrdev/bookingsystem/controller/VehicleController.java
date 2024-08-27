package io.github.junrdev.bookingsystem.controller;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Vehicle;
import io.github.junrdev.bookingsystem.service.VehicleService;
import io.github.junrdev.bookingsystem.util.mappers.VehicleMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMappers vehicleMappers;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleMappers vehicleMappers) {
        this.vehicleService = vehicleService;
        this.vehicleMappers = vehicleMappers;
    }


    // Create or Update a Vehicle
    @PostMapping("/new")
    public ResponseEntity<VehicleDto> saveVehicle(@RequestBody VehicleDto dto) {
        Vehicle savedVehicle = vehicleService.saveVehicle(dto);
        return ResponseEntity.ok(vehicleMappers.toDto(savedVehicle));
    }

    // Retrieve a Vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle1 = vehicleService.getVehicleById(id);
        return vehicle1.map(vehicle -> ResponseEntity.ok(vehicleMappers.toDto(vehicle)))
                .orElseThrow(() -> new NotFoundException("Failed to get vehicle with id " + id));
    }

    // Retrieve all Vehicles
    @GetMapping("/")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService
                .getAllVehicles()
                .stream()
                .map(vehicleMappers::toDto)
                .toList();
        return ResponseEntity.ok(vehicles);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<VehicleDto> updateVehicle(
            @PathVariable("id") Long id,
            @RequestBody VehicleDto dto
    ) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, dto));
    }

    // Delete a Vehicle by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
