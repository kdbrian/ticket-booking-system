package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Embedded
    private Kyc kyc;

    @Temporal(TemporalType.DATE)
    private Date dateJoined;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Embedded
    private Location location;

    private String locationArea;

    private Boolean isActive;

    @ElementCollection
    private List<String> images;
}
