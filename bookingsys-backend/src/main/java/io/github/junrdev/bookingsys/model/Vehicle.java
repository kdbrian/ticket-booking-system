package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Long leavingTime = System.currentTimeMillis();

    private List<String> additionalInfo = new ArrayList<>();

    //additional fields
    private String vehicleName;

    private Long timeOfTravel;

    private Long updateslug;

    public Vehicle() {
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

    public Long getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Long leavingTime) {
        this.leavingTime = leavingTime;
    }

    public List<String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Long getTimeOfTravel() {
        return timeOfTravel;
    }

    public void setTimeOfTravel(Long timeOfTravel) {
        this.timeOfTravel = timeOfTravel;
    }

    public Long getUpdateslug() {
        return updateslug;
    }

    public void setUpdateslug(Long updateslug) {
        this.updateslug = updateslug;
    }

    public Seat getSeat(String seatNo) {
        if (this.seats != null && !this.getSeats().isEmpty()) {
            Optional<Seat> seat = this.seats.stream()
                    .filter(seat1 -> seat1.getSeatNo().equalsIgnoreCase(seatNo))
                    .findFirst();
            return seat.orElse(null);
        }
        return null;
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
                ", vehicleName='" + vehicleName + '\'' +
                ", timeOfTravel=" + timeOfTravel +
                ", updateslug=" + updateslug +
                '}';
    }
}

