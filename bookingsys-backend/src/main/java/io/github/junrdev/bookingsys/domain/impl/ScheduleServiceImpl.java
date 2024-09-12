package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.ScheduleDto;
import io.github.junrdev.bookingsys.model.Company;
import io.github.junrdev.bookingsys.model.Schedule;
import io.github.junrdev.bookingsys.repository.CompanyRepository;
import io.github.junrdev.bookingsys.repository.ScheduleRepository;
import io.github.junrdev.bookingsys.service.ScheduleService;
import io.github.junrdev.bookingsys.util.mappers.ScheduleMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper, ScheduleRepository scheduleRepository, CompanyRepository companyRepository) {
        this.scheduleMapper = scheduleMapper;
        this.scheduleRepository = scheduleRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public Schedule saveSchedule(ScheduleDto dto) {
        Optional<Company> optionalCompany = companyRepository.findById(dto.getCompanyId());
        Schedule schedule = scheduleMapper.scheduleDtoToSchedule(dto);
        if (optionalCompany.isEmpty())
            throw new NotFoundException("Company " + dto.getCompanyId() + " Not found.");
        optionalCompany.ifPresent(schedule::setCompany);
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getScheduleById(String id) {
        return scheduleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Schedule %s not found", id)));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
//                .stream().map(scheduleMapper::scheduleToScheduleDto).toList();
    }

    @Override
    public boolean deleteSchedule(String id) {
        if (scheduleRepository.existsById(id)) {
            //remove associated objects
            scheduleRepository.deleteById(id);
            return true;
        } else
            throw new NotFoundException(String.format("Schedule %s not found", id));
    }
}
