package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
