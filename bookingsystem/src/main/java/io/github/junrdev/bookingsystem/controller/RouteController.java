package io.github.junrdev.bookingsystem.controller;


import io.github.junrdev.bookingsystem.dto.RouteDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // Create or Update a Route
    @PostMapping("/create")
    public ResponseEntity<RouteDto> createOrUpdateRoute(@RequestBody RouteDto dto) {
        Route savedRoute = routeService.saveRoute(dto);
        return ResponseEntity.ok(RouteDto
                .builder()
                .id(savedRoute.getId())
                .scheduleId(savedRoute.getSchedule().getId())
                .vehicles(savedRoute.getVehicles())
                .fromLocation(savedRoute.getFromLocation())
                .toLocation(savedRoute.getToLocation())
                .fromLocationName(savedRoute.getFromLocationName())
                .toLocationName(savedRoute.getToLocationName())
                .build()
        );
    }

    // Retrieve a Route by ID
    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id).map(route ->
                ResponseEntity.ok(
                        RouteDto
                                .builder()
                                .id(route.getId())
                                .scheduleId(route.getSchedule().getId())
                                .vehicles(route.getVehicles())
                                .fromLocation(route.getFromLocation())
                                .toLocation(route.getToLocation())
                                .fromLocationName(route.getFromLocationName())
                                .toLocationName(route.getToLocationName())
                                .build()
                )
        ).orElseThrow(() -> new NotFoundException("Failed to fetch route with id " + id));
    }

    // Retrieve all Routes
    @GetMapping("/")
    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes.stream().map(route ->
                RouteDto.builder()
                        .id(route.getId())
                        .scheduleId(route.getSchedule().getId())
                        .toLocationName(route.getToLocationName())
                        .fromLocationName(route.getFromLocationName())
                        .toLocation(route.getToLocation())
                        .fromLocation(route.getFromLocation())
                        .vehicles(route.getVehicles())
                        .build()
        ).toList());
    }

    // Delete a Route by ID
    @PatchMapping("/{id}/update")
    public ResponseEntity<RouteDto> deleteRoute(@PathVariable Long id, @RequestBody RouteDto dto) {
        if (routeService.getRouteById(id).isPresent()) {
            Route saved = routeService.updateRoute(id,dto);

            dto.setId(saved.getId());
            dto.setFromLocation(saved.getFromLocation());
            dto.setToLocation(saved.getToLocation());
            dto.setFromLocationName(saved.getFromLocationName());
            dto.setToLocationName(saved.getToLocationName());
            dto.setVehicles(saved.getVehicles());
            dto.setScheduleId(saved.getSchedule().getId());

            return ResponseEntity.ok(dto);
        }
        throw new NotFoundException("Failed to fetch route with id " + id);
    }

    // Delete a Route by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
