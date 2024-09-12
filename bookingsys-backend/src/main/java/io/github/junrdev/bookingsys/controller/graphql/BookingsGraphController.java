package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.BookingDto;
import io.github.junrdev.bookingsys.model.Booking;
import io.github.junrdev.bookingsys.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingsGraphController {

    private final BookingService bookingService;

    @Autowired
    public BookingsGraphController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @QueryMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @QueryMapping
    public Booking getBookingById(@Argument String id) {
        return bookingService.getBookingById(id);
    }

    @MutationMapping
    public Booking saveBooking(@Argument BookingDto dto) {
        return bookingService.saveBooking(dto);
    }

    @MutationMapping
    public boolean deleteBooking(@Argument String bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    @MutationMapping
    public Booking cancelBooking(@Argument String bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
}
