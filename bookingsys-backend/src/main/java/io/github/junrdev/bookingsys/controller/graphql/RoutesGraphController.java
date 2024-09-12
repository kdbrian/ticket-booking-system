package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.RouteDto;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RoutesGraphController {

    private final RouteService routeService;

    @Autowired
    public RoutesGraphController(RouteService routeService) {
        this.routeService = routeService;
    }

    @QueryMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @QueryMapping
    public Route getRouteById(@Argument String  id){
        return routeService.getRouteById(id);
    }

    @MutationMapping
    public Route saveRoute(@Argument RouteDto dto) {
        return routeService.saveRoute(dto);
    }

    @MutationMapping
    public boolean deleteRoute(@Argument String id) {
        return routeService.deleteRoute(id);
    }
}
