package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Document
public class Vehicle {

    @Id
    private String vehicleId;

    @DBRef
    private Route route;

    private List<Seat> seats = new ArrayList<>();

    private Long seatCount;

    private Double price;

    private Double discount;

    private LocalTime leavingTime;

    private List<String> additionalInfo = new ArrayList<>();

    transient private Map<String, Seat> seatMap;

    public Vehicle() {
    }

    public Vehicle(Route route, List<Seat> seats, Long seatCount, Double price, Double discount, LocalTime leavingTime, List<String> additionalInfo) {
        this.route = route;
        this.seats = seats;
        this.seatCount = seatCount;
        this.price = price;
        this.discount = discount;
        this.leavingTime = leavingTime;
        this.additionalInfo = additionalInfo;
        if (seats != null)
            this.seatMap = seats.stream().collect(Collectors.toMap(Seat::getSeatNo, seat -> seat));
    }

    public Vehicle(String vehicleId, Route route, List<Seat> seats, Long seatCount, Double price, Double discount, LocalTime leavingTime) {
        this.vehicleId = vehicleId;
        this.route = route;
        this.seats = seats;
        this.seatCount = seatCount;
        this.price = price;
        this.discount = discount;
        this.leavingTime = leavingTime;
        if (seats != null)
            this.seatMap = seats.stream().collect(Collectors.toMap(Seat::getSeatNo, seat -> seat));
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
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
        this.seatMap = seats.stream().collect(Collectors.toMap(Seat::getSeatNo, seat -> seat));
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

    public List<String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Map<String, Seat> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(Map<String, Seat> seatMap) {
        this.seatMap = seatMap;
    }

    public void processSeatRequest(List<String> seats) {
        seats.forEach(requestedSeat -> {
            Seat seat = seatMap.get(requestedSeat);
            if (seat != null && !seat.getOccupied()) {
                seat.occupy();
            }
        });
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", route=" + route +
                ", seats=" + seats +
                ", seatCount=" + seatCount +
                ", price=" + price +
                ", discount=" + discount +
                ", leavingTime=" + leavingTime +
                ", additionalInfo=" + additionalInfo +
                ", seatMap=" + seatMap +
                '}';
    }
}

