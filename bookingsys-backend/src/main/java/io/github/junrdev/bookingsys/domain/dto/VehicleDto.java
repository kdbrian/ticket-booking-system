package io.github.junrdev.bookingsys.domain.dto;

import io.github.junrdev.bookingsys.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class VehicleDto {
    private String vehicleId;
    private String routeId;
    private Double price;
    private Double discount;
    private Long leavingTime;
    private Long seatCount;
    private List<Seat> seats = new ArrayList<>();
    private List<String> additionalInfo = new ArrayList<>();

    public VehicleDto() {
    }

    public VehicleDto(String vehicleId, String routeId, Double price, Double discount, Long leavingTime, Long seatCount, List<Seat> seats, List<String> additionalInfo) {
        this.vehicleId = vehicleId;
        this.routeId = routeId;
        this.price = price;
        this.discount = discount;
        this.leavingTime = leavingTime;
        this.seatCount = seatCount;
        this.seats = seats;
        this.additionalInfo = additionalInfo;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
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

    public Long getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Long leavingTime) {
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

    public List<String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "vehicleId='" + vehicleId + '\'' +
                ", routeId='" + routeId + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", leavingTime=" + leavingTime +
                ", seatCount=" + seatCount +
                ", seats=" + seats +
                ", additionalInfo=" + additionalInfo +
                '}';
    }
}
