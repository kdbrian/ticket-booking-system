package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.model.SubCounty;

import java.util.List;

public interface RouteService {

    // Create or Update a Route
    Route saveRoute(RouteDto dto);

    Route updateRoute(String id, RouteDto dto);

    // Retrieve a Route by ID
    Route getRouteById(String routeId);

    // Retrieve all Routes
    List<Route> getAllRoutes();

    // Delete a Route by ID
    boolean deleteRoute(String routeId);

    // Additional business logic methods as needed, such as finding routes by criteria
    List<Route> findByCounty(String countyName);

    List<Route> findBySubCounty(String subCountyName);

    List<Route> findByCountyAndSubCounty(String countyName, String subCountyName);
}
