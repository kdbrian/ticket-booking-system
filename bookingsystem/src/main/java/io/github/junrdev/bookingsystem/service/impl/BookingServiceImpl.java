package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.model.Booking;
import io.github.junrdev.bookingsystem.repository.BookingRepository;
import io.github.junrdev.bookingsystem.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    @Transactional
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
