package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "from_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "from_longitude"))
    })
    private Location fromLocation;

    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "to_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "to_longitude"))
    })
    private Location toLocation;

    private String fromLocationName;

    private String toLocationName;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
