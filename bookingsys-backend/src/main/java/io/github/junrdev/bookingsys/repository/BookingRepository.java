package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Booking;
import io.github.junrdev.bookingsys.model.Client;
import io.github.junrdev.bookingsys.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByClient(Client client);

    List<Booking> findByVehicle(Vehicle vehicle);

}
