package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.VehicleDto;
import io.github.junrdev.bookingsys.model.Seat;
import io.github.junrdev.bookingsys.model.Vehicle;
import io.github.junrdev.bookingsys.repository.RouteRepository;
import io.github.junrdev.bookingsys.repository.VehicleRepository;
import io.github.junrdev.bookingsys.service.VehicleService;
import io.github.junrdev.bookingsys.util.mappers.VehicleMappers;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleMappers vehicleMappers;
    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;
//    private final CompanyRepository companyRepository;


    @Autowired
    public VehicleServiceImpl(VehicleMappers vehicleMappers, VehicleRepository vehicleRepository, RouteRepository routeRepository) {
        this.vehicleMappers = vehicleMappers;
        this.vehicleRepository = vehicleRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public Vehicle saveVehicle(VehicleDto dto) {
        return routeRepository.findById(dto.getRouteId())
                .map(route -> {
                            Vehicle vehicle = vehicleMappers.toEntity(dto);
                            vehicle.setRoute(route);
                            vehicle.setSeats(generateSeats(dto.getSeatCount()));

                            Vehicle saved = vehicleRepository.save(vehicle);
                            route.addVehicle(saved);

                            routeRepository.save(route);

                            return saved;
                        }
                )
                .orElseThrow(() -> new NotFoundException("Route " + dto.getRouteId() + " not found."));
    }

    @Override
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id)
//                .map(vehicleMappers::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with id %s not found.", id)));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public boolean deleteVehicle(String id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format("Vehicle with id %s not found.", id));
    }

    @Override
    public Vehicle updateVehicle(String id, VehicleDto dto) {
        if (vehicleRepository.existsById(id)) {
            return vehicleRepository.save(vehicleMappers.toEntity(dto));
        } else throw new NotFoundException(String.format("Vehicle with id %s not found.", id));
    }

    @Override
    public List<Vehicle> getVehiclesByRoute(String routeId) {
        return routeRepository.findById(routeId)
                .map(vehicleRepository::findByRoute)
                .orElseThrow(() -> new NotFoundException(String.format("Route with id %s not found.", routeId)));
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
