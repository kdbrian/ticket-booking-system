package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.dto.ScheduleDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Route;
import io.github.junrdev.bookingsystem.model.Schedule;
import io.github.junrdev.bookingsystem.repository.CompanyRepository;
import io.github.junrdev.bookingsystem.repository.ScheduleRepository;
import io.github.junrdev.bookingsystem.service.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public Schedule saveSchedule(ScheduleDto dto) {
        return companyRepository.findById(dto.getCompanyId())
                .map((company ->
                        scheduleRepository.save(
                                Schedule.builder()
                                        .company(company)
                                        .phone(dto.getPhone())
                                        .routes(dto.getRoutes().stream().map(routeDto ->
                                                Route.builder()
                                                        .fromLocation(routeDto.getFromLocation())
                                                        .toLocation(routeDto.getToLocation())
                                                        .fromLocationName(routeDto.getFromLocationName())
                                                        .toLocationName(routeDto.getToLocationName())
                                                        .vehicles(routeDto.getVehicles())
                                                        .build()
                                        ).toList())
                                        .build()
                        )
                )).orElseThrow(() -> new NotFoundException("Company not found with id " + dto.getCompanyId()));

    }

    @Override
    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

}
