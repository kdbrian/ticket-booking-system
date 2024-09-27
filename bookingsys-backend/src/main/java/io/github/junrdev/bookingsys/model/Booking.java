package io.github.junrdev.bookingsys.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Booking {

    @Id
    private String id;

    @DBRef
    private Client client;

    @DBRef
    private Vehicle vehicle;

    private List<Seat> seats;

    private Boolean isActive = true;

    private LocalDateTime bookingDate;

    private Double totalPrice;

    private BookingStatus bookingStatus;
    private BOOKING_TYPE bookingType;
    private PAYMENT_STATUS paymentStatus;
    private Long updateSlug= System.currentTimeMillis();

    public Long getUpdateSlug() {
        return updateSlug;
    }

    public void setUpdateSlug(Long updateSlug) {
        this.updateSlug = updateSlug;
    }

    // Enum for Booking Status
    public enum BookingStatus {
        CONFIRMED,
        PENDING,
        CANCELLED,
        COMPLETED
    }

    public enum BOOKING_TYPE {
        ONE_WAY, ROUND_TRIP
    }

    public enum PAYMENT_STATUS {
        INCOMPLETE, PENDING, COMPLETE
    }

    public BOOKING_TYPE getBookingType() {
        return bookingType;
    }

    public void setBookingType(BOOKING_TYPE bookingType) {
        this.bookingType = bookingType;
    }

    public PAYMENT_STATUS getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PAYMENT_STATUS paymentStatus) {
        this.paymentStatus = paymentStatus;
    }



    public Booking() {
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", vehicle=" + vehicle +
                ", seats=" + seats +
                ", isActive=" + isActive +
                ", bookingDate=" + bookingDate +
                ", totalPrice=" + totalPrice +
                ", bookingStatus=" + bookingStatus +
                ", bookingType=" + bookingType +
                ", paymentStatus=" + paymentStatus +
                ", updateSlug=" + updateSlug +
                '}';
    }
}
