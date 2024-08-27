package io.github.junrdev.bookingsystem.controller;


import io.github.junrdev.bookingsystem.dto.RouteDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.service.RouteService;
import io.github.junrdev.bookingsystem.util.mappers.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final RouteMapper routeMapper = RouteMapper.INSTANCE;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Create or Update a Route
    @PostMapping("/create")
    public ResponseEntity<RouteDto> createOrUpdateRoute(@RequestBody RouteDto dto) {
        Route savedRoute = routeService.saveRoute(dto);
        return ResponseEntity.ok(routeMapper.routeToRouteDto(savedRoute));
    }

    // Retrieve a Route by ID
    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id).map(route -> ResponseEntity.ok(routeMapper.routeToRouteDto(route))
        ).orElseThrow(() -> new NotFoundException("Failed to fetch route with id " + id));
    }

    // Retrieve all Routes
    @GetMapping("/")
    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes.stream().map(routeMapper::routeToRouteDto).toList());
    }

    // Delete a Route by ID
    @PatchMapping("/{id}/update")
    public ResponseEntity<RouteDto> deleteRoute(@PathVariable Long id, @RequestBody RouteDto dto) {
        if (routeService.getRouteById(id).isPresent()) {
            Route saved = routeService.updateRoute(id, dto);
            return ResponseEntity.ok(routeMapper.routeToRouteDto(saved));
        }
        throw new NotFoundException("Failed to fetch route with id " + id);
    }

    // Delete a Route by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
