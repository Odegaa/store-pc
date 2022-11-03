package com.storepc.repositories;

import com.storepc.models.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByCountryName(String countryName);

    boolean existsByCountryName(String countryName);

}