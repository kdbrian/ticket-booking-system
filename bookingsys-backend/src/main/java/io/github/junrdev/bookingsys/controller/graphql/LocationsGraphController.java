package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.SubCounty;
import io.github.junrdev.bookingsys.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocationsGraphController {

    private final LocationService locationService;

    @Autowired
    public LocationsGraphController(LocationService locationService) {
        this.locationService = locationService;
    }

    @QueryMapping
    public List<County> getCounties() {
        return locationService.getCounties();
    }


    @QueryMapping
    public List<SubCounty> getSubCounties() {
        return locationService.getSubCounties();
    }


    @QueryMapping
    public List<SubCounty> getSubCountiesByCountyName(@Argument String countyName) {
        return locationService.getSubCountiesByCountyName(countyName);
    }

    @QueryMapping
    public List<SubCounty> getSubCountiesByCountyNumber(@Argument int countyNumber) {
        return locationService.getSubCountiesByCountyNumber(countyNumber);
    }

}
