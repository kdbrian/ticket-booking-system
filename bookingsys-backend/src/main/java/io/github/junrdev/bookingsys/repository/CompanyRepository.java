package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}