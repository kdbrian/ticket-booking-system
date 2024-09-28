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
    private String countyName;
    private String subCountyName;

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

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getSubCountyName() {
        return subCountyName;
    }

    public void setSubCountyName(String subCountyName) {
        this.subCountyName = subCountyName;
    }

    public void setSubCounty(String subCounty) {
        this.subCountyName = subCounty;
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
                ", county='" + countyName + '\'' +
                ", subCounty='" + subCountyName + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
