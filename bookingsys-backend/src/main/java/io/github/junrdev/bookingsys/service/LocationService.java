package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.SubCounty;

import java.util.List;

public interface LocationService {

    List<County> getCounties();

    List<SubCounty> getSubCounties();

    List<SubCounty> getSubCountiesByCountyNumber(int countyNumber);

    List<SubCounty> getSubCountiesByCountyName(String countyName);


}
