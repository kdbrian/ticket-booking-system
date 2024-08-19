package io.github.junrdev.bookingsystem.error.model;

import lombok.Builder;

@Builder
public class CompanyNotFoundException extends RuntimeException {
    private String message;

    public CompanyNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
