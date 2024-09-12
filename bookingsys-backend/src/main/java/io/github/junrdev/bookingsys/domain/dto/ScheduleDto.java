package io.github.junrdev.bookingsys.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDto {
    private String scheduleId;
    private String companyId;
    private List<RouteDto> routes = new ArrayList<>();
    private String phone;

    public ScheduleDto() {
    }

    public ScheduleDto(String id, String companyId, List<RouteDto> routes, String phone) {
        this.scheduleId = id;
        this.companyId = companyId;
        this.routes = routes;
        this.phone = phone;
    }

    public ScheduleDto(String companyId, List<RouteDto> routes, String phone) {
        this.companyId = companyId;
        this.routes = routes;
        this.phone = phone;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDto> routes) {
        this.routes = routes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "companyId=" + companyId +
                ", routes=" + routes +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getId() {
        return scheduleId;
    }

    public void setId(String id) {
        this.scheduleId = id;
    }
}
