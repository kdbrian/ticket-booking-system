package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
