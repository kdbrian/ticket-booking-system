package io.github.junrdev.bookingsys.util.mappers;

import io.github.junrdev.bookingsys.domain.dto.BookingDto;
import io.github.junrdev.bookingsys.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SeatMapper.class})
public interface BookingMapper {

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "vehicleId", target = "vehicle.vehicleId")
    Booking toEntity(BookingDto dto);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "vehicle.vehicleId", target = "vehicleId")
    BookingDto toDto(Booking booking);

    // MapStruct will automatically use SeatMapper for List<SeatDto> <-> List<Seat>
}

