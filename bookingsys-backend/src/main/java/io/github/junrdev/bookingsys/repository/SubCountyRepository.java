package io.github.junrdev.bookingsys.repository;

import io.github.junrdev.bookingsys.model.SubCounty;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface SubCountyRepository extends MongoRepository<SubCounty, String> {

    List<SubCounty> findByCountyNumber(int countyNumber);

    List<SubCounty> findByCountyName(String countyName);

    Optional<SubCounty> findBySubCountyName(String subCountyName);


}
