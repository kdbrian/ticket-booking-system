package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.CompanyDto;
import io.github.junrdev.bookingsys.model.Company;
import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.SubCounty;
import io.github.junrdev.bookingsys.repository.CompanyRepository;
import io.github.junrdev.bookingsys.repository.CountyRepository;
import io.github.junrdev.bookingsys.repository.SubCountyRepository;
import io.github.junrdev.bookingsys.service.CompanyService;
import io.github.junrdev.bookingsys.util.mappers.CompanyMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CountyRepository countyRepository;
    private final SubCountyRepository subCountyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CountyRepository countyRepository, SubCountyRepository subCountyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.countyRepository = countyRepository;
        this.subCountyRepository = subCountyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public Company saveCompany(CompanyDto companyDto) {
        Company company = companyMapper.companyDToCompany(companyDto);
        County county = countyRepository.findByCountyName(companyDto.countyName())
                .orElseThrow(() -> new NotFoundException("County " + companyDto.countyName() + " Not found."));
        SubCounty subCounty = subCountyRepository.findBySubCountyName(companyDto.subCountyName())
                .orElseThrow(() -> new NotFoundException("sub-county " + companyDto.countyName() + " Not found."));

        company.setCounty(county);
        company.setSubCounty(subCounty);

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
        if (companyRepository.existsById(id)) {
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
