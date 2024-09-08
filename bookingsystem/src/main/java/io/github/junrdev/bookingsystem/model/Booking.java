package io.github.junrdev.bookingsystem.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private Vehicle vehicleId;

    @Embedded
    private Seat seat;

    private Boolean isActive = true;

    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    // Enum for Booking Status
    public enum BookingStatus {
        CONFIRMED,
        PENDING,
        CANCELLED,
        COMPLETED
    }

    public Booking() {
    }

    public Booking(Long id, Client client, Schedule schedule, Vehicle vehicleId, Seat seat, Boolean isActive, LocalDateTime bookingDate, BookingStatus bookingStatus) {
        this.id = id;
        this.client = client;
        this.schedule = schedule;
        this.vehicleId = vehicleId;
        this.seat = seat;
        this.isActive = isActive;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    public Booking(Client client, Schedule schedule, Vehicle vehicleId, Seat seat, Boolean isActive, LocalDateTime bookingDate, BookingStatus bookingStatus) {
        this.client = client;
        this.schedule = schedule;
        this.vehicleId = vehicleId;
        this.seat = seat;
        this.isActive = isActive;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", client=" + client +
                ", schedule=" + schedule +
                ", vehicleId=" + vehicleId +
                ", seat=" + seat +
                ", isActive=" + isActive +
                ", bookingDate=" + bookingDate +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}
