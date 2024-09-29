package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.Route;
import io.github.junrdev.bookingsys.model.SubCounty;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RouteRepository extends MongoRepository<Route, String> {

//    List<Route> findByCounty(County county);
//
//    List<Route> findBySubCounty(SubCounty subCounty);
//
//    List<Route> findByCountyAndSubCounty(County county, SubCounty subCounty);
}
