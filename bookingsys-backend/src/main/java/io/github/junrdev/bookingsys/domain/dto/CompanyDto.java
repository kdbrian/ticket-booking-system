package io.github.junrdev.bookingsys.domain.dto;

import io.github.junrdev.bookingsys.model.Location;

import java.util.List;

public record CompanyDto (
        String fullName,
        Location location,
        List<String> images,
        String locationArea,
        String email,
        String phone
){


}
