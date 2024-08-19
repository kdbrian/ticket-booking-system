package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.error.model.CompanyNotFoundException;
import io.github.junrdev.bookingsystem.model.Company;
import io.github.junrdev.bookingsystem.repository.CompanyRepository;
import io.github.junrdev.bookingsystem.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        } else
            throw new CompanyNotFoundException("Company not found with id " + id);
    }

    @Override
    @Transactional
    public Company updateCompany(Long id, Company company) {
        if (companyRepository.existsById(id)) {
            return companyRepository.save(company);
        }
        throw new CompanyNotFoundException("Company not found with id " + id);
    }

    @Override
    public void deactivateAccount(Long id, Company company) {
        if (companyRepository.existsById(id)) {
            company.setIsActive(false);
            companyRepository.save(company);
        } else
            throw new CompanyNotFoundException("Company not found with id " + id);
    }
}
