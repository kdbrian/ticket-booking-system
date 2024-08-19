package io.github.junrdev.bookingsystem.service;

import io.github.junrdev.bookingsystem.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    // Create or Update a Route
    Route saveRoute(Route route);

    // Retrieve a Route by ID
    Optional<Route> getRouteById(Long routeId);

    // Retrieve all Routes
    List<Route> getAllRoutes();

    // Delete a Route by ID
    void deleteRoute(Long routeId);

    // Additional business logic methods as needed, such as finding routes by criteria
}
