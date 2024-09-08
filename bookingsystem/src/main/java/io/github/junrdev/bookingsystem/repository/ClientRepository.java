package io.github.junrdev.bookingsystem.repository;

import io.github.junrdev.bookingsystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
