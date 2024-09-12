package io.github.junrdev.bookingsys.controller.rest;

import io.github.junrdev.bookingsys.domain.dto.BookingDto;
import io.github.junrdev.bookingsys.model.Booking;
import io.github.junrdev.bookingsys.service.BookingService;
import io.github.junrdev.bookingsys.util.mappers.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookingDto>> getBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings().stream().map(bookingMapper::toDto).toList());
    }

    @PostMapping("/new")
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto) {
        return new ResponseEntity<>(bookingMapper.toDto(bookingService.saveBooking(dto)), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingDto> cancelBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingMapper.toDto(bookingService.cancelBooking(bookingId)));
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
