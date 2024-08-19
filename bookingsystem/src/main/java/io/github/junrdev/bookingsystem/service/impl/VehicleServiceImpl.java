package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.error.model.CompanyNotFoundException;
import io.github.junrdev.bookingsystem.model.Vehicle;
import io.github.junrdev.bookingsystem.repository.RouteRepository;
import io.github.junrdev.bookingsystem.repository.VehicleRepository;
import io.github.junrdev.bookingsystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;

    // Create or Update a Vehicle
    @Transactional
    public Vehicle saveVehicle(VehicleDto dto) {
        return routeRepository.findById(dto.getRouteId())
                .map(route -> vehicleRepository.save(
                        Vehicle.builder()
                                .route(route)
                                .leavingTime(dto.getLeavingTime())
                                .discount(dto.getDiscount())
                                .price(dto.getPrice())
                               .build()
                )).orElseThrow(() -> new CompanyNotFoundException("Route not found"));
    }

    // Retrieve a Vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Retrieve all Vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Delete a Vehicle by ID
    @Transactional
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

}
