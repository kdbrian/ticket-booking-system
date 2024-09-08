package io.github.junrdev.bookingsystem.repository;

import io.github.junrdev.bookingsystem.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
