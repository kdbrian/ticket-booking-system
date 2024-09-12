package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.repository.RouteRepository;
import io.github.junrdev.bookingsys.repository.ScheduleRepository;
import io.github.junrdev.bookingsys.service.RouteService;
import io.github.junrdev.bookingsys.util.mappers.RouteMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ScheduleRepository scheduleRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
        this.routeMapper = routeMapper;
    }

    @Override
    public Route saveRoute(RouteDto dto) {
        Route route = routeMapper.routeDtoToRoute(dto);
        return scheduleRepository.findById(dto.getScheduleId())
                .map(schedule -> {
                    route.setSchedule(schedule);
                    Route saved = routeRepository.save(route);
                    schedule.addRoute(saved);
                    scheduleRepository.save(schedule);
                    return saved;
                })
                .orElseThrow(() -> new NotFoundException("Schedule " + dto.getScheduleId() + " not found."));
    }

    @Override
    public Route updateRoute(String id, RouteDto dto) {
        if (routeRepository.existsById(id)) {
            return routeRepository.save(routeMapper.routeDtoToRoute(dto));
        } else throw new NotFoundException(String.format("Route with id %s not found", id));
    }

    @Override
    public Route getRouteById(String routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException(String.format("Route with id %s not found", routeId)));
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public boolean deleteRoute(String routeId) {
        if (routeRepository.existsById(routeId)) {
            routeRepository.deleteById(routeId);
            return true;
        }
        else throw new NotFoundException(String.format("Route with id %s not found", routeId));
    }
}
