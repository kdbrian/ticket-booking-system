package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.model.SubCounty;
import io.github.junrdev.bookingsys.repository.CountyRepository;
import io.github.junrdev.bookingsys.repository.RouteRepository;
import io.github.junrdev.bookingsys.repository.ScheduleRepository;
import io.github.junrdev.bookingsys.repository.SubCountyRepository;
import io.github.junrdev.bookingsys.service.RouteService;
import io.github.junrdev.bookingsys.util.mappers.RouteMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger log = LoggerFactory.getLogger(RouteServiceImpl.class);
    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;
    private final RouteMapper routeMapper;
    private final CountyRepository countyRepository;
    private final SubCountyRepository subCountyRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ScheduleRepository scheduleRepository, RouteMapper routeMapper, CountyRepository countyRepository, SubCountyRepository subCountyRepository) {
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
        this.routeMapper = routeMapper;
        this.countyRepository = countyRepository;
        this.subCountyRepository = subCountyRepository;
    }

    @Override
    public Route saveRoute(RouteDto dto) {
        log.info("dto {}", dto);
        Route route = routeMapper.routeDtoToRoute(dto);
        return scheduleRepository.findById(dto.getScheduleId())
                .map(schedule -> {
                    County county = countyRepository.findByCountyNameContains(dto.getCountyName())
                            .orElseThrow(() -> new NotFoundException("County " + dto.getCountyName() + " not found."));

                    SubCounty subCounty = subCountyRepository.findBySubCountyName(dto.getSubCountyName())
                            .orElseThrow(() -> new NotFoundException("Sub-County " + dto.getSubCountyName() + " not found."));

                    route.setCounty(county);
                    route.setSubCounty(subCounty);

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
        } else throw new NotFoundException(String.format("Route with id %s not found", routeId));
    }

    @Override
    public List<Route> findByCounty(String countyName) {
        County county = countyRepository.findByCountyNameContains(countyName)
                .orElseThrow(() -> new NotFoundException("County " + countyName + " not found."));

        return routeRepository.findByCounty(county);
    }

    @Override
    public List<Route> findBySubCounty(String subCountyName) {
        SubCounty subCounty = subCountyRepository.findBySubCountyName(subCountyName)
                .orElseThrow(() -> new NotFoundException("Sub-County " + subCountyName + " not found."));

        return routeRepository.findBySubCounty(subCounty);
    }

    @Override
    public List<Route> findByCountyAndSubCounty(String countyName, String subCountyName) {

        County county = countyRepository.findByCountyNameContains(countyName)
                .orElseThrow(() -> new NotFoundException("County " + countyName + " not found."));

        SubCounty subCounty = subCountyRepository.findBySubCountyName(subCountyName)
                .orElseThrow(() -> new NotFoundException("County " + countyName + " not found."));

        return routeRepository.findByCountyAndSubCounty(county, subCounty);
    }

}
