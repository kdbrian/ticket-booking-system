package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.VehicleDto;
import io.github.junrdev.bookingsys.model.Vehicle;
import io.github.junrdev.bookingsys.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VehiclesGraphController {

    private final VehicleService vehicleService;

    @Autowired
    public VehiclesGraphController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @QueryMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @MutationMapping("saveVehicle")
    public Vehicle addVehicle(@Argument VehicleDto dto) {
        return vehicleService.saveVehicle(dto);
    }

    @QueryMapping
    public Vehicle getVehicleById(@Argument String id) {
        return vehicleService.getVehicleById(id);
    }

    @MutationMapping
    public Vehicle updateVehicle(@Argument String id, @Argument VehicleDto dto) {
        return vehicleService.updateVehicle(id, dto);
    }

    @MutationMapping
    public boolean deleteVehicle(@Argument String id) {
        return vehicleService.deleteVehicle(id);
    }

    @QueryMapping
    public List<Vehicle> getVehiclesByRoute(String routeId){
        return vehicleService.getVehiclesByRoute(routeId);
    }
}
