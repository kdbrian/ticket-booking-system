package io.github.junrdev.bookingsystem.service;

import io.github.junrdev.bookingsystem.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking saveBooking(Booking booking);

    Optional<Booking> getBookingById(Long id);

    List<Booking> getAllBookings();

    void deleteBooking(Long id);
}
