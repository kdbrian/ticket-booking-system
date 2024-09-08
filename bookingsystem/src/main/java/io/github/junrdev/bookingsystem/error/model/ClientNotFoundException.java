package io.github.junrdev.bookingsystem.error.model;

//@Builder
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
