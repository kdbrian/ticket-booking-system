package io.github.junrdev.bookingsystem.dto;

import io.github.junrdev.bookingsystem.model.Location;
import io.github.junrdev.bookingsystem.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDto {
    private Long id;
    private Long scheduleId;
    private Location fromLocation;
    private Location toLocation;
    private String fromLocationName;
    private String toLocationName;
    private List<VehicleDto> vehicles;
}
