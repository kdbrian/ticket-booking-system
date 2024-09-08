package io.github.junrdev.bookingsystem.dto;

import io.github.junrdev.bookingsystem.model.Location;

import java.util.List;

public class RouteDto {
    private Long id;
    private Long scheduleId;
    private Location fromLocation;
    private Location toLocation;
    private String fromLocationName;
    private String toLocationName;
    private List<VehicleDto> vehicles;

    public RouteDto() {
    }

    public RouteDto(Long scheduleId, Location fromLocation, Location toLocation, String fromLocationName, String toLocationName, List<VehicleDto> vehicles) {
        this.scheduleId = scheduleId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.fromLocationName = fromLocationName;
        this.toLocationName = toLocationName;
        this.vehicles = vehicles;
    }

    public RouteDto(Long id, Long scheduleId, Location fromLocation, Location toLocation, String fromLocationName, String toLocationName, List<VehicleDto> vehicles) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.fromLocationName = fromLocationName;
        this.toLocationName = toLocationName;
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
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

    public List<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }

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
