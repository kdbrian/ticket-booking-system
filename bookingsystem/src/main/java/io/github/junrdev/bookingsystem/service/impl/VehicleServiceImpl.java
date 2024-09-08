package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.model.Seat;
import io.github.junrdev.bookingsystem.model.Vehicle;
import io.github.junrdev.bookingsystem.repository.RouteRepository;
import io.github.junrdev.bookingsystem.repository.VehicleRepository;
import io.github.junrdev.bookingsystem.service.VehicleService;
import io.github.junrdev.bookingsystem.util.mappers.VehicleMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;

    private final VehicleMappers vehicleMappers = VehicleMappers.INSTANCE;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, RouteRepository routeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.routeRepository = routeRepository;
    }

    // Create or Update a Vehicle
    @Transactional
    public Vehicle saveVehicle(VehicleDto dto) {
        Vehicle vehicle = vehicleMappers.toEntity(dto);
        vehicle.setSeats(generateSeats(dto.getSeatCount()));

        return routeRepository.findById(dto.getRouteId())
                .map(route -> vehicle)
                .orElseThrow(() -> new NotFoundException("Route not found"));
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

    @Override
    public VehicleDto updateVehicle(Long id, VehicleDto dto) {
        if (vehicleRepository.existsById(id)) {
            Vehicle vehicle = vehicleMappers.toEntity(dto);
            Route route = routeRepository.findById(dto.getRouteId())
                    .orElseThrow(() -> new NotFoundException("Failed to get route with id " + dto.getRouteId()));

            vehicle.setRoute(route);
            Vehicle saved = vehicleRepository.save(vehicle);
            return vehicleMappers.toDto(saved);
        }
        throw new NotFoundException("Failed to get vehicle with id " + id);
    }

    public List<Seat> generateSeats(Long seatCount) {
        List<Seat> seats = new ArrayList<>();

        // Initialize seat labels
        char rowLabel = 'A';
        int seatNumber = 1;

        for (int i = 0; i < seatCount; i++) {
            String seatNo = rowLabel + String.valueOf(seatNumber);
            seats.add(new Seat(seatNo, false));

            seatNumber++;
            if (seatNumber > 10) {  // Assuming 10 seats per row
                seatNumber = 1;
                rowLabel++;
            }
        }

        return seats;
    }
}
