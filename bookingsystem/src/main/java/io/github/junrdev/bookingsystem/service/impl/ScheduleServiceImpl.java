package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.dto.ScheduleDto;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import io.github.junrdev.bookingsystem.model.Schedule;
import io.github.junrdev.bookingsystem.repository.CompanyRepository;
import io.github.junrdev.bookingsystem.repository.ScheduleRepository;
import io.github.junrdev.bookingsystem.service.ScheduleService;
import io.github.junrdev.bookingsystem.util.mappers.ScheduleMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    private final ScheduleMapper scheduleMapper = ScheduleMapper.INSTANCE;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, CompanyRepository companyRepository) {
        this.scheduleRepository = scheduleRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public ScheduleDto saveSchedule(ScheduleDto dto) {
        return companyRepository.findById(dto.getCompanyId())
                .map((company -> {
                    Schedule saved = scheduleRepository.save(scheduleMapper.scheduleDtoToSchedule(dto));
                    return scheduleMapper.scheduleToScheduleDto(saved);
                })).orElseThrow(() -> new NotFoundException("Company not found with id " + dto.getCompanyId()));

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
