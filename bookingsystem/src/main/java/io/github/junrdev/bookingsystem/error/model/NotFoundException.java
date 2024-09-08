package io.github.junrdev.bookingsystem.error.model;

public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
