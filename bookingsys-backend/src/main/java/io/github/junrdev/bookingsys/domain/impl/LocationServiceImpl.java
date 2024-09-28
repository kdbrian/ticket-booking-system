package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.model.County;
import io.github.junrdev.bookingsys.model.SubCounty;
import io.github.junrdev.bookingsys.repository.CountyRepository;
import io.github.junrdev.bookingsys.repository.SubCountyRepository;
import io.github.junrdev.bookingsys.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final CountyRepository countyRepository;
    private final SubCountyRepository subCountyRepository;

    @Autowired
    public LocationServiceImpl(CountyRepository countyRepository, SubCountyRepository subCountyRepository) {
        this.countyRepository = countyRepository;
        this.subCountyRepository = subCountyRepository;
    }

    @Override
    public List<County> getCounties() {
        return countyRepository.findAll();
    }

    @Override
    public List<SubCounty> getSubCounties() {
        return subCountyRepository.findAll();
    }

    @Override
    public List<SubCounty> getSubCountiesByCountyNumber(int countyNumber) {
        return subCountyRepository.findByCountyNumber(countyNumber);
    }

    @Override
    public List<SubCounty> getSubCountiesByCountyName(String countyName) {
        return subCountyRepository.findByCountyName(countyName);
    }
}
