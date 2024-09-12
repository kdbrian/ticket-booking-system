package io.github.junrdev.bookingsys.domain.dto;

import io.github.junrdev.bookingsys.model.Location;
import io.github.junrdev.bookingsys.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class RouteDto {

    private String id;
    private String scheduleId;
    private Location fromLocation;
    private Location toLocation;
    private String fromLocationName;
    private String toLocationName;
    private List<Vehicle> vehicles = new ArrayList<>();

    public RouteDto() {
    }

    public RouteDto(String scheduleId, Location fromLocation, Location toLocation, String fromLocationName, String toLocationName, List<Vehicle> vehicles) {
        this.scheduleId = scheduleId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.fromLocationName = fromLocationName;
        this.toLocationName = toLocationName;
        this.vehicles = vehicles;
    }

    public RouteDto(String id, String scheduleId, Location fromLocation, Location toLocation, String fromLocationName, String toLocationName, List<Vehicle> vehicles) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.fromLocationName = fromLocationName;
        this.toLocationName = toLocationName;
        this.vehicles = vehicles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public String getFromLocationName() {
        return fromLocationName;
    }

    public void setFromLocationName(String fromLocationName) {
        this.fromLocationName = fromLocationName;
    }

    public String getToLocationName() {
        return toLocationName;
    }

    public void setToLocationName(String toLocationName) {
        this.toLocationName = toLocationName;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

//    public void addVehicle(Vehicle vehicle){
//        this.vehicles.add(vehicle);
//    }

    @Override
    public String toString() {
        return "RouteDto{" +
                "id=" + id +
                ", scheduleId=" + scheduleId +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                ", fromLocationName='" + fromLocationName + '\'' +
                ", toLocationName='" + toLocationName + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
