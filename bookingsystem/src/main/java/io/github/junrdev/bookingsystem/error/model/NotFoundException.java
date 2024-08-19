package io.github.junrdev.bookingsystem.error.model;

import lombok.Builder;

@Builder
public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
