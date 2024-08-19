package io.github.junrdev.bookingsystem.error.model;

import lombok.Builder;

//@Builder
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
