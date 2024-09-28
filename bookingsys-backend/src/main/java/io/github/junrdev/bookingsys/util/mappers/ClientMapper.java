package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.ClientDto;
import io.github.junrdev.bookingsys.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "county.countyName", target = "county")
    @Mapping(source = "subCounty.subCountyName", target = "subCounty")
    ClientDto toDto(Client client);

    @Mapping(target = "county.countyName",  source= "county")
    @Mapping(target = "subCounty.subCountyName", source = "subCounty")
    Client toEntity(ClientDto dto);

}
