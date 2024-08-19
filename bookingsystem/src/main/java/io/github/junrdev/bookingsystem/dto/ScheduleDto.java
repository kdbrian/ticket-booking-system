package io.github.junrdev.bookingsystem.dto;

import io.github.junrdev.bookingsystem.model.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {
    private Long companyId;
    private List<RouteDto> routes;
    private String phone;

}
