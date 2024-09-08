package io.github.junrdev.bookingsystem.repository;

import io.github.junrdev.bookingsystem.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}