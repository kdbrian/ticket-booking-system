package io.github.junrdev.bookingsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private Boolean isActive = true;

    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    // Enum for Booking Status
    public enum BookingStatus {
        CONFIRMED,
        PENDING,
        CANCELLED,
        COMPLETED
    }
}
