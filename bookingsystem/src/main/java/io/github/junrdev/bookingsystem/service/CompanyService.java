package io.github.junrdev.bookingsystem.service;

import io.github.junrdev.bookingsystem.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company saveCompany(Company company);

    Optional<Company> getCompanyById(Long id);

    List<Company> getAllCompanies();

    void deleteCompany(Long id);

    Company updateCompany(Long id, Company company);

    void deactivateAccount(Long id, Company company);
}
