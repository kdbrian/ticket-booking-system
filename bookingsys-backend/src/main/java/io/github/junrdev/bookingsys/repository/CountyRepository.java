package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.County;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CountyRepository extends MongoRepository<County, String> {
    Optional<County> findByCountyName(String countyName);
}
