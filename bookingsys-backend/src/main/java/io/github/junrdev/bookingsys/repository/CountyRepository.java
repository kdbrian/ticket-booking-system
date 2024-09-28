package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.County;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountyRepository extends MongoRepository<County, String> {
}
