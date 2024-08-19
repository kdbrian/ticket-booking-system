package io.github.junrdev.bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDto {
    private Long routeId;
    private Double price;
    private Double discount;
    private Time leavingTime;
    private Long seatCount;
}
