package io.github.junrdev.bookingsys.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Route {

    private static final Logger log = LoggerFactory.getLogger(Route.class);
    @Id
    private String id;

    private Location fromLocation;

    private Location toLocation;

    private String fromLocationName;

    private String toLocationName;

    @DBRef
    transient private Schedule schedule;

    private List<Vehicle> vehicles = new ArrayList<>();

    public Route() {
    }

    public Route(Location fromLocation, Location toLocation, String fromLocationName, String toLocationName, Schedule schedule, List<Vehicle> vehicles) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.fromLocationName = fromLocationName;
        this.toLocationName = toLocationName;
        this.schedule = schedule;
        this.vehicles = vehicles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        log.info("invoked");
        if (this.vehicles == null)
            this.vehicles = new ArrayList<>();
        this.vehicles.add(vehicle);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                ", fromLocationName='" + fromLocationName + '\'' +
                ", toLocationName='" + toLocationName + '\'' +
                ", schedule=" + schedule +
                ", vehicles=" + vehicles +
                '}';
    }
}
