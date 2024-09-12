package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    List<Vehicle> findByRoute(Route route);

}
