package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.model.Company;
import io.github.junrdev.bookingsys.repository.CompanyRepository;
import io.github.junrdev.bookingsys.service.CompanyService;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> getCompanyById(String id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean deleteCompany(String id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format("Company with id %s not found.", id));
    }

    @Override
    public Company updateCompany(String id, Company company) {
        if (companyRepository.existsById(id))
            return companyRepository.save(company);
        throw new NotFoundException(String.format("Company with id %s not found.", id));
    }

    @Override
    public void deactivateAccount(String id, Company company) {
        if (companyRepository.existsById(id)) {
            company.setActive(false);
            companyRepository.save(company);
        }
        throw new NotFoundException(String.format("Company with id %d not found.", id));
    }
}
