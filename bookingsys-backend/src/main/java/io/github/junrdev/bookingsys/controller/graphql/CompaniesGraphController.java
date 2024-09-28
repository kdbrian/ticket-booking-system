package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.CompanyDto;
import io.github.junrdev.bookingsys.model.Company;
import io.github.junrdev.bookingsys.service.CompanyService;
import io.github.junrdev.bookingsys.util.mappers.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompaniesGraphController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompaniesGraphController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @QueryMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @MutationMapping
    public Company createCompany(@Argument CompanyDto dto) {
        Company company = companyMapper.companyDToCompany(dto);
        return companyService.saveCompany(company);
    }

    @MutationMapping
    public Boolean deleteCompany(@Argument String id){
        return companyService.deleteCompany(id);
    }


}
