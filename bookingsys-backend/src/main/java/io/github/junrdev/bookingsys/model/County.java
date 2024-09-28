package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "counties")
public record County(
       @Id String id,
        @Field("county_number") int countyNumber,
        @Field("name") String countyName
) {
}
