package io.github.junrdev.bookingsystem.dto;

import io.github.junrdev.bookingsystem.model.Seat;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class VehicleDto {
    private Long vehicleId;
    private Long routeId;
    private Double price;
    private Double discount;
    private LocalTime leavingTime;
    private Long seatCount;
    private List<Seat> seats;

    public VehicleDto() {
    }

    public VehicleDto(Long vehicleId, Long routeId, Double price, Double discount, LocalTime leavingTime, Long seatCount, List<Seat> seats) {
        this.vehicleId = vehicleId;
        this.routeId = routeId;
        this.price = price;
        this.discount = discount;
        this.leavingTime = leavingTime;
        this.seatCount = seatCount;
        this.seats = seats;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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

    public Long getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Long seatCount) {
        this.seatCount = seatCount;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "vehicleId=" + vehicleId +
                ", routeId=" + routeId +
                ", price=" + price +
                ", discount=" + discount +
                ", leavingTime=" + leavingTime +
                ", seatCount=" + seatCount +
                ", seats=" + seats +
                '}';
    }
}
