package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ElementCollection
    @CollectionTable(name = "vehicle_seats", joinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Seat> seats;

    private Long seatCount;

    @Column(nullable = false)
    private Double price;

    private Double discount;

    @Column(nullable = false)
    private Time leavingTime;
}

