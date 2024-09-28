package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.CompanyDto;
import io.github.junrdev.bookingsys.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    @Mapping(source = "county.countyName", target = "countyName")
    @Mapping(source = "subCounty.subCountyName", target = "subCountyName")
    CompanyDto companyToCompanyDto(Company company);

    Company companyDToCompany(CompanyDto companyDto);


}
