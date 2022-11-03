package com.storepc.repositories;

import com.storepc.models.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByCityName(String cityName);

    boolean existsByCityName(String cityName);
}