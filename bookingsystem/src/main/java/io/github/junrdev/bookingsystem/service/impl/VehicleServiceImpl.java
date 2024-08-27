package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.dto.VehicleDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Seat;
import io.github.junrdev.bookingsystem.model.Vehicle;
import io.github.junrdev.bookingsystem.repository.RouteRepository;
import io.github.junrdev.bookingsystem.repository.VehicleRepository;
import io.github.junrdev.bookingsystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .map(route ->
                        vehicleRepository.save(
                                Vehicle.builder()
                                        .route(route)
                                        .leavingTime(dto.getLeavingTime())
                                        .discount(dto.getDiscount())
                                        .price(dto.getPrice())
                                        .seats(generateSeats(dto.getSeatCount()))
                                        .seatCount(dto.getSeatCount())
                                        .build())

                ).orElseThrow(() -> new NotFoundException("Route not found"));
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
            vehicleRepository.save(
                    Vehicle.builder()
                            .vehicleId(dto.getVehicleId())
                            .route(routeRepository.findById(dto.getRouteId()).orElseThrow(() -> new NotFoundException("Failed to get route with id " + dto.getRouteId())))
                            .seatCount(dto.getSeatCount())
                            .seats(dto.getSeats())
                            .price(dto.getPrice())
                            .discount(dto.getDiscount())
                            .leavingTime(dto.getLeavingTime())
                            .build()
            );
            return dto;
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
