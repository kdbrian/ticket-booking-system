package io.github.junrdev.bookingsys.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "subcounties")
public record SubCounty(
        @Id String id,
        @Field("county_number") int countyNumber,
        @Field("county_name") String countyName,
        @Field("name") String subCountyName
) {
}
