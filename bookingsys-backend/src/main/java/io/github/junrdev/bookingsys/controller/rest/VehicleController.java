package io.github.junrdev.bookingsys.controller.rest;

import io.github.junrdev.bookingsys.domain.dto.VehicleDto;
import io.github.junrdev.bookingsys.model.Vehicle;
import io.github.junrdev.bookingsys.service.VehicleService;
import io.github.junrdev.bookingsys.util.mappers.VehicleMappers;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "CRUD on vehicles")
public class VehicleController {


    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleMappers vehicleMappers) {
        this.vehicleService = vehicleService;
    }


    // Create or Update a Vehicle
    @PostMapping("/new")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody VehicleDto dto) {
        Vehicle savedVehicle = vehicleService.saveVehicle(dto);
        return ResponseEntity.ok(savedVehicle);
    }

    // Retrieve a Vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") String id) {
        Vehicle vehicle1 = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle1);
    }

    // Retrieve all Vehicles
    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable("id") String id,
            @RequestBody VehicleDto dto
    ) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, dto));
    }

    // Delete a Vehicle by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/route")
    public ResponseEntity<List<Vehicle>> getRouteVehicles(
            @RequestParam("id") String routeId
    ) {
        log.info("Route {},", routeId);
        System.out.println("Invoked");
        return ResponseEntity.ok(vehicleService.getVehiclesByRoute(routeId));
    }

}
