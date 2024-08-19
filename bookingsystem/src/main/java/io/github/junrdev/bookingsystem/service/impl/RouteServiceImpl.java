package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.repository.RouteRepository;
import io.github.junrdev.bookingsystem.service.RouteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {


    @Autowired
    private RouteRepository routeRepository;

    // Create or Update a Route
    @Transactional
    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    // Retrieve a Route by ID
    @Override
    public Optional<Route> getRouteById(Long routeId) {
        return routeRepository.findById(routeId);
    }

    // Retrieve all Routes
    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Delete a Route by ID
    @Transactional
    @Override
    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    // You can add more methods here if needed, such as finding routes by specific criteria.
}
