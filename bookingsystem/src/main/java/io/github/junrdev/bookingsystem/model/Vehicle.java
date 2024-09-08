package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq", allocationSize = 1)
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ElementCollection
    @CollectionTable(name = "vehicle_seats", joinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Seat> seats;

    private Long seatCount;

    @Column(nullable = false)
    private Double price;

    private Double discount;

    @Column(nullable = false)
    private LocalTime leavingTime;

    public Vehicle() {
    }

    public Vehicle(Route route, List<Seat> seats, Long seatCount, Double price, Double discount, LocalTime leavingTime) {
        this.route = route;
        this.seats = seats;
        this.seatCount = seatCount;
        this.price = price;
        this.discount = discount;
        this.leavingTime = leavingTime;
    }

    public Vehicle(Long vehicleId, Route route, List<Seat> seats, Long seatCount, Double price, Double discount, LocalTime leavingTime) {
        this.vehicleId = vehicleId;
        this.route = route;
        this.seats = seats;
        this.seatCount = seatCount;
        this.price = price;
        this.discount = discount;
        this.leavingTime = leavingTime;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Long getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Long seatCount) {
        this.seatCount = seatCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public LocalTime getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(LocalTime leavingTime) {
        this.leavingTime = leavingTime;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", route=" + route +
                ", seats=" + seats +
                ", seatCount=" + seatCount +
                ", price=" + price +
                ", discount=" + discount +
                ", leavingTime=" + leavingTime +
                '}';
    }
}

