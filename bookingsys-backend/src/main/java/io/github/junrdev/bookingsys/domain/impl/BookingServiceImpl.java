package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.BookingDto;
import io.github.junrdev.bookingsys.model.Booking;
import io.github.junrdev.bookingsys.model.Client;
import io.github.junrdev.bookingsys.model.Seat;
import io.github.junrdev.bookingsys.model.Vehicle;
import io.github.junrdev.bookingsys.model.enums.BOOKING_STATUS;
import io.github.junrdev.bookingsys.model.enums.PAYMENT_STATUS;
import io.github.junrdev.bookingsys.repository.BookingRepository;
import io.github.junrdev.bookingsys.repository.ClientRepository;
import io.github.junrdev.bookingsys.repository.VehicleRepository;
import io.github.junrdev.bookingsys.service.BookingService;
import io.github.junrdev.bookingsys.service.ClientService;
import io.github.junrdev.bookingsys.util.mappers.BookingMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final BookingMapper bookingMapper;


    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ClientRepository clientRepository, ClientService clientService, VehicleRepository vehicleRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
        this.bookingMapper = bookingMapper;
    }


    @Override
    public Booking saveBooking(BookingDto dto) {
        // Create a new booking
        Booking booking = new Booking();

        // Fetch client
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Fetch vehicle
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Set the client and vehicle in booking
        booking.setClient(client);
        booking.setVehicle(vehicle);

        // Process the seats for the booking and update the vehicle's seat availability
        List<Seat> vehicleSeats = vehicle.getSeats();

        // Filter out the seats that are not occupied and can be booked
        List<Seat> bookedSeats = dto.getSeats().stream()
                .map(seatDto -> {
                    String seatNo = seatDto.getSeatNo();

                    // Find the seat in the vehicle's seat list
                    Seat seat = vehicleSeats.stream()
                            .filter(vs -> vs.getSeatNo().equalsIgnoreCase(seatNo))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundException(String.format("Seat %s not found in vehicle", seatNo)));

                    // Check if the seat is already occupied
                    if (!seat.getOccupied()) {
                        // Mark the seat as occupied in the vehicle and return it to be added to booking
                        seat.occupy(); // Mark seat as occupied
                        return seat; // This seat will be added to booking
                    } else {
                        // If the seat is already occupied, return null (we'll filter this out later)
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filter out the seats that were already occupied
                .collect(Collectors.toList());

        if (bookedSeats.isEmpty())
            throw new NotFoundException("All selected seats are occupied.");

        // Set the booked seats in the booking
        booking.setSeats(bookedSeats);

        // Update the vehicle's seats list in MongoDB
        vehicle.setSeats(vehicleSeats);
        vehicle.setUpdateslug(System.currentTimeMillis());
        vehicleRepository.save(vehicle);

        // Set other fields in booking
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingStatus(dto.getBookingStatus());
        booking.setPaymentStatus(PAYMENT_STATUS.PENDING); // Set payment status to pending
        booking.setBookingType(dto.getBookingType());

        // Save the booking with the newly booked seats
        return bookingRepository.save(booking);
    }


    @Override
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking " + id + " not found"));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking cancelBooking(String bookingId) {
        //find vehicle release occupied seats
        return bookingRepository.findById(bookingId)
                .map(booking1 -> {
                    if (booking1.getUpdateSlug() != System.currentTimeMillis()) {
                        booking1.setActive(false);
                        booking1.setBookingStatus(BOOKING_STATUS.CANCELLED);
                        booking1.setPaymentStatus(PAYMENT_STATUS.INCOMPLETE);

                        //get vehicle
                        Vehicle vehicle = vehicleRepository.findById(booking1.getVehicle().getVehicleId())
                                .orElseThrow(() -> new NotFoundException("Vehicle " + booking1.getVehicle().getVehicleId() + " not found"));

                        //find seats
                        List<String> booked = booking1.getSeats().stream().map(Seat::getSeatNo).toList();

                        List<Seat> vehicleSeats = vehicle.getSeats().stream().peek(seat -> {
                            if (booked.contains(seat.getSeatNo())) {
                                seat.release();
                            }
                        }).toList();

                        vehicle.setSeats(vehicleSeats);
                        booking1.setSeats(Collections.emptyList());

                        vehicle.setUpdateslug(System.currentTimeMillis());
                        vehicleRepository.save(vehicle);
                        booking1.setUpdateSlug(System.currentTimeMillis());
                        return bookingRepository.save(booking1);
                    } else
                        throw new NotFoundException("Booking " + booking1.getId() + " not found");
                })
                .orElseThrow(() -> new NotFoundException("Booking " + bookingId + " not found"));
    }

    @Override
    public boolean deleteBooking(String bookingId) {
        return bookingRepository.findById(bookingId).map(booking -> {
            //get vehicle
            Vehicle vehicle = vehicleRepository.findById(booking.getVehicle().getVehicleId())
                    .orElseThrow(() -> new NotFoundException("Vehicle " + booking.getVehicle().getVehicleId() + " not found"));

            //find seats
            List<String> booked = booking.getSeats().stream().map(Seat::getSeatNo).toList();

            List<Seat> vehicleSeats = vehicle.getSeats().stream().peek(seat -> {
                if (booked.contains(seat.getSeatNo()) && seat.getOccupied()) {
                    seat.release();
                }
            }).toList();

            vehicle.setSeats(vehicleSeats);
            vehicle.setUpdateslug(System.currentTimeMillis());
            vehicleRepository.save(vehicle);
            bookingRepository.deleteById(bookingId);
            return true;
        }).orElseThrow(
                () -> new NotFoundException(String.format("Booking %s, not found", bookingId)));
    }

    @Override
    public List<Booking> getClientBookings(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(String.format("Client %s, not found", clientId)));

        return bookingRepository.findByClient(client);
    }

    @Override
    public List<Booking> getVehicleBookings(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle %s, not found", vehicleId)));

        return bookingRepository.findByVehicle(vehicle);
    }
}
