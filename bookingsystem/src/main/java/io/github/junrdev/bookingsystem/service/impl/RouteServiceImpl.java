package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.dto.RouteDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.repository.RouteRepository;
import io.github.junrdev.bookingsystem.repository.ScheduleRepository;
import io.github.junrdev.bookingsystem.service.RouteService;
import io.github.junrdev.bookingsystem.util.mappers.RouteMapper;
import io.github.junrdev.bookingsystem.util.mappers.VehicleMappers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {


    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;
    private final RouteMapper routeMapper = RouteMapper.INSTANCE;
    private final VehicleMappers vehicleMappers = VehicleMappers.INSTANCE;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ScheduleRepository scheduleRepository) {
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // Create or Update a Route
    @Transactional
    @Override
    public Route saveRoute(RouteDto dto) {
        return scheduleRepository
                .findById(dto.getScheduleId())
                .map(schedule -> routeRepository.save(routeMapper.routeDtoToRoute(dto)))
                .orElseThrow(() -> new NotFoundException("Failed to fetch schedule with id " + dto.getScheduleId()));
    }

    @Transactional
    @Override
    public Route updateRoute(Long id, RouteDto dto) {
        return routeRepository.findById(id).map(route -> {
                    route.setFromLocation(dto.getFromLocation());
                    route.setToLocation(dto.getToLocation());
                    route.setFromLocationName(dto.getFromLocationName());
                    route.setToLocationName(dto.getToLocationName());
                    route.setSchedule(route.getSchedule());
                    route.setVehicles(dto.getVehicles().stream().map(vehicleMappers::toEntity).toList());
                    return routeRepository.save(route);
                }
        ).orElseThrow(() -> new NotFoundException("Failed to fetch route with id " + id));
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
        if (routeRepository.existsById(routeId))
            routeRepository.deleteById(routeId);
        else
            throw new NotFoundException("Failed to fetch route with id " + routeId);
    }

    // You can add more methods here if needed, such as finding routes by specific criteria.
}
