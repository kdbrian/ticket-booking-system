package io.github.junrdev.bookingsys.util.mappers;


import io.github.junrdev.bookingsys.domain.dto.SeatDto;
import io.github.junrdev.bookingsys.model.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    SeatDto toDto(Seat seat);

    Seat toEntity(SeatDto seatDto);

    // MapStruct will handle the collections automatically
    List<SeatDto> toDtoList(List<Seat> seats);

    List<Seat> toEntityList(List<SeatDto> seatDtos);
}

