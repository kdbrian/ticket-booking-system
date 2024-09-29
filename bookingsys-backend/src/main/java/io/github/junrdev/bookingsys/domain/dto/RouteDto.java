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

    private String fromCountyName;
    private String fromSubCountyName;

    private String toCountyName;
    private String toSubCountyName;

    private List<VehicleDto> vehicles = new ArrayList<>();

    public RouteDto() {
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

    public List<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }

    public String getFromCountyName() {
        return fromCountyName;
    }

    public void setFromCountyName(String fromCountyName) {
        this.fromCountyName = fromCountyName;
    }

    public String getFromSubCountyName() {
        return fromSubCountyName;
    }

    public void setFromSubCountyName(String fromSubCountyName) {
        this.fromSubCountyName = fromSubCountyName;
    }

    public String getToCountyName() {
        return toCountyName;
    }

    public void setToCountyName(String toCountyName) {
        this.toCountyName = toCountyName;
    }

    public String getToSubCountyName() {
        return toSubCountyName;
    }

    public void setToSubCountyName(String toSubCountyName) {
        this.toSubCountyName = toSubCountyName;
    }

    @Override
    public String toString() {
        return "RouteDto{" +
                "id='" + id + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                ", fromLocationName='" + fromLocationName + '\'' +
                ", toLocationName='" + toLocationName + '\'' +
                ", fromCountyName='" + fromCountyName + '\'' +
                ", fromSubCountyName='" + fromSubCountyName + '\'' +
                ", toCountyName='" + toCountyName + '\'' +
                ", toSubCountyName='" + toSubCountyName + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }

}
