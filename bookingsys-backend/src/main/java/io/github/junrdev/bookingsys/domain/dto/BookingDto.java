package io.github.junrdev.bookingsys.domain.dto;

import io.github.junrdev.bookingsys.model.enums.BOOKING_STATUS;
import io.github.junrdev.bookingsys.model.enums.BOOKING_TYPE;
import io.github.junrdev.bookingsys.model.enums.PAYMENT_STATUS;

import java.time.LocalDate;
import java.util.List;

public class BookingDto {
    private String id;

    private String clientId;

    private String vehicleId;

    private List<SeatDto> seats;

    private LocalDate bookingDate = LocalDate.now();
    private BOOKING_STATUS bookingStatus = BOOKING_STATUS.PENDING;
    private PAYMENT_STATUS paymentStatus = PAYMENT_STATUS.PENDING;
    private BOOKING_TYPE bookingType = BOOKING_TYPE.ONE_WAY;

    public BookingDto() {
    }

    public BookingDto(String id, String clientId, String vehicleId, List<SeatDto> seats, LocalDate bookingDate, BOOKING_STATUS bookingStatus, PAYMENT_STATUS paymentStatus, BOOKING_TYPE bookingType) {
        this.id = id;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.seats = seats;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
        this.bookingType = bookingType;
    }

    public BookingDto(String clientId, String vehicleId, List<SeatDto> seats, LocalDate bookingDate, BOOKING_STATUS bookingStatus, PAYMENT_STATUS paymentStatus, BOOKING_TYPE bookingType) {
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.seats = seats;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
        this.bookingType = bookingType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BOOKING_STATUS getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BOOKING_STATUS BOOKINGSTATUS) {
        this.bookingStatus = BOOKINGSTATUS;
    }

    public BOOKING_TYPE getBookingType() {
        return bookingType;
    }

    public void setBookingType(BOOKING_TYPE bookingType) {
        this.bookingType = bookingType;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", seats=" + seats +
                ", bookingDate=" + bookingDate +
                ", bookingStatus=" + bookingStatus +
                ", paymentStatus=" + paymentStatus +
                ", bookingType=" + bookingType +
                '}';
    }
}

