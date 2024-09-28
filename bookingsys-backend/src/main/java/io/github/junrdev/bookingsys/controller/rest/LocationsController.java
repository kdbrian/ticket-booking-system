package io.github.junrdev.bookingsys.controller.rest;

import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.SubCounty;
import io.github.junrdev.bookingsys.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private final LocationService locationService;

    @Autowired
    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/counties")
    public ResponseEntity<List<County>> getCounties() {
        return ResponseEntity.ok(locationService.getCounties());
    }


    @GetMapping("/sub-counties")
    public ResponseEntity<List<SubCounty>> getSubCounties() {
        return ResponseEntity.ok(locationService.getSubCounties());
    }


    @GetMapping("/sub-counties/")
    public ResponseEntity<List<SubCounty>> getSubCountiesBy(
            @RequestParam(value = "countyName", required = false) String countyName,
            @RequestParam(value = "countyNumber", required = false) Integer countyNumber
    ) {
        if (countyName != null)
            return ResponseEntity.ok(locationService.getSubCountiesByCountyName(countyName));
        else
            return ResponseEntity.ok(locationService.getSubCountiesByCountyNumber(countyNumber));
    }

}
