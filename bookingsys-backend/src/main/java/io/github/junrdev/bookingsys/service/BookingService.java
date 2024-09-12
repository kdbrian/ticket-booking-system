package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.domain.dto.BookingDto;
import io.github.junrdev.bookingsys.model.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(BookingDto dto);

    Booking getBookingById(String id);

    List<Booking> getAllBookings();

    Booking cancelBooking(String bookingId);

    boolean deleteBooking(String bookingId);
}
