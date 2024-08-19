package io.github.junrdev.bookingsystem.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kyc {

    private String identificationNumber;
    private String uploadedFormLink;

    @Enumerated(EnumType.STRING)
    private FormEvaluation formEvaluation = FormEvaluation.IN_PROGRESS;

    // Enum for Form Evaluation Status
    public enum FormEvaluation {
        DENIED,
        ACCEPTED,
        IN_PROGRESS,
        REFILL
    }

}