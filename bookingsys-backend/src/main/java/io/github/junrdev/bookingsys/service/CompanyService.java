package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.domain.dto.CompanyDto;
import io.github.junrdev.bookingsys.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company saveCompany(CompanyDto companyDto);

    Optional<Company> getCompanyById(String id);

    List<Company> getAllCompanies();

    boolean deleteCompany(String id);

    Company updateCompany(String id, Company company);

    void deactivateAccount(String id, Company company);
}
