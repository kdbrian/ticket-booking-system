package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, String> {
}
