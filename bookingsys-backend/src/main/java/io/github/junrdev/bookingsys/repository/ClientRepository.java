package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
