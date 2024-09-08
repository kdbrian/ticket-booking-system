package io.github.junrdev.bookingsystem.dto;

import java.util.List;

public class ScheduleDto {
    private Long companyId;
    private List<RouteDto> routes;
    private String phone;

    public ScheduleDto() {
    }

    public ScheduleDto(Long companyId, List<RouteDto> routes, String phone) {
        this.companyId = companyId;
        this.routes = routes;
        this.phone = phone;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
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
}
