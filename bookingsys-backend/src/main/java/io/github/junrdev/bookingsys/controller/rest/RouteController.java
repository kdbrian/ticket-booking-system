package io.github.junrdev.bookingsys.controller.rest;


import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.service.RouteService;
import io.github.junrdev.bookingsys.util.mappers.RouteMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteController(RouteService routeService, RouteMapper routeMapper) {
        this.routeService = routeService;
        this.routeMapper = routeMapper;
    }

    // Create or Update a Route
    @PostMapping("/new")
    public ResponseEntity<RouteDto> createOrUpdateRoute(@RequestBody RouteDto dto) {
        Route savedRoute = routeService.saveRoute(dto);
        return ResponseEntity.ok(routeMapper.routeToRouteDto(savedRoute));
    }

    // Retrieve a Route by ID
    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable String id) {
        return ResponseEntity.ok(routeMapper.routeToRouteDto(routeService.getRouteById(id)));
    }

    // Retrieve all Routes
    @GetMapping("/")
    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes.stream().map(routeMapper::routeToRouteDto).toList());
    }

    @GetMapping("/county")
    public ResponseEntity<List<RouteDto>> getRoutesByCounty(
            @RequestParam("countyName") String countyName
    ) {
        return ResponseEntity.ok(
                routeService
                        .findByCounty(countyName)
                        .stream()
                        .map(routeMapper::routeToRouteDto).toList());
    }

    @GetMapping("/subcounty")
    public ResponseEntity<List<RouteDto>> getRoutesBySubCounty(
            @RequestParam("subCountyName") String subCountyName
    ) {
        return ResponseEntity.ok(
                routeService
                        .findBySubCounty(subCountyName)
                        .stream()
                        .map(routeMapper::routeToRouteDto).toList());
    }


    @GetMapping("/region")
    public ResponseEntity<List<RouteDto>> getRoutesBySubCounty(
            @RequestParam("countyName") String countyName,
            @RequestParam("subCountyName") String subCountyName
    ) {
        return ResponseEntity.ok(
                routeService
                        .findByCountyAndSubCounty(countyName, subCountyName)
                        .stream()
                        .map(routeMapper::routeToRouteDto).toList());
    }


    // Delete a Route by ID
    @PatchMapping("/{id}/update")
    public ResponseEntity<RouteDto> deleteRoute(@PathVariable String id, @RequestBody RouteDto dto) {
        if (routeService.getRouteById(id) != null) {
            Route saved = routeService.updateRoute(id, dto);
            return ResponseEntity.ok(routeMapper.routeToRouteDto(saved));
        }
        throw new NotFoundException("Failed to fetch route with id " + id);
    }

    // Delete a Route by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRoute(@PathVariable String id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

}
