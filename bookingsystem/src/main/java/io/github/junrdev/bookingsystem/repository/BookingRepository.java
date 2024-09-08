package io.github.junrdev.bookingsystem.repository;

import io.github.junrdev.bookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
