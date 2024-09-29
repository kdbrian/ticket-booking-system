package io.github.junrdev.bookingsys.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public record UnifiedLocationCountySubCounty(
        @DBRef
        County county,
        @DBRef
        SubCounty subCounty
) {
}
