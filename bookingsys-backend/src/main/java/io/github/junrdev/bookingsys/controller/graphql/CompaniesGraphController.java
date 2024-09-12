package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.CompanyDto;
import io.github.junrdev.bookingsys.model.Company;
import io.github.junrdev.bookingsys.service.CompanyService;
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

    @Autowired
    public CompaniesGraphController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @QueryMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @MutationMapping
    public Company createCompany(@Argument CompanyDto dto) {
        return companyService.saveCompany(new Company(dto.fullName(), dto.email(), dto.phone(), dto.location(), dto.locationArea(), dto.images()));
    }

    @MutationMapping
    public Boolean deleteCompany(@Argument String id){
        return companyService.deleteCompany(id);
    }


}
