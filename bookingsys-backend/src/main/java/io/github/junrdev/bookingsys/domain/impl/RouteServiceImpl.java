package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.model.SubCounty;
import io.github.junrdev.bookingsys.model.UnifiedLocationCountySubCounty;
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

                    // all are optional
                    County fromcounty = null;
                    if (dto.getFromCountyName() != null)
                        fromcounty = countyRepository.findByCountyNameContains(dto.getFromCountyName())
                                .orElseThrow(() -> new NotFoundException("County " + dto.getFromCountyName() + " not found."));

                    County toCounty = null;
                    if (dto.getToCountyName() != null)
                        toCounty = countyRepository.findByCountyNameContains(dto.getToCountyName())
                                .orElseThrow(() -> new NotFoundException("County " + dto.getToCountyName() + " not found."));

                    SubCounty fromSubCounty = null;
                    if (dto.getFromSubCountyName() != null)
                        fromSubCounty = subCountyRepository.findBySubCountyName(dto.getFromSubCountyName())
                                .orElseThrow(() -> new NotFoundException("Sub-County " + dto.getFromSubCountyName() + " not found."));

                    SubCounty toSubCounty = null;
                    if (dto.getToSubCountyName() != null)
                        toSubCounty = subCountyRepository.findBySubCountyName(dto.getToSubCountyName())
                                .orElseThrow(() -> new NotFoundException("Sub-County " + dto.getToSubCountyName() + " not found."));

                    //set unified fields -> might be nulls
                    route.setFromLocationCountySubCounty(
                            new UnifiedLocationCountySubCounty(fromcounty, fromSubCounty)
                    );
                    route.setToLocationCountySubCounty(
                            new UnifiedLocationCountySubCounty(toCounty, toSubCounty)
                    );

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

        return routeRepository.findAll()
                .stream()
                .filter(route ->
                        route.getFromLocationCountySubCounty().county().equals(county) ||
                                route.getToLocationCountySubCounty().county().equals(county)
                ).toList();
    }

    @Override
    public List<Route> findBySubCounty(String subCountyName) {
        SubCounty subCounty = subCountyRepository.findBySubCountyName(subCountyName)
                .orElseThrow(() -> new NotFoundException("Sub-County " + subCountyName + " not found."));

        return routeRepository.findAll()
                .stream()
                .filter(route ->
                        route.getFromLocationCountySubCounty().subCounty().equals(subCounty) ||
                                route.getToLocationCountySubCounty().subCounty().equals(subCounty)
                ).toList();
    }

    @Override
    public List<Route> findByCountyAndSubCounty(String countyName, String subCountyName) {

        County county = countyRepository.findByCountyNameContains(countyName)
                .orElseThrow(() -> new NotFoundException("County " + countyName + " not found."));

        SubCounty subCounty = subCountyRepository.findBySubCountyName(subCountyName)
                .orElseThrow(() -> new NotFoundException("County " + countyName + " not found."));

        return routeRepository.findAll()
                .stream()
                .filter(route ->
                        route.getFromLocationCountySubCounty().subCounty().equals(subCounty) ||
                                route.getFromLocationCountySubCounty().county().equals(county) ||
                                route.getToLocationCountySubCounty().county().equals(county) ||
                                route.getToLocationCountySubCounty().subCounty().equals(subCounty)
                ).toList();
    }

}
