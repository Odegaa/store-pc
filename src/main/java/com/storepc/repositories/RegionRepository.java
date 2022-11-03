package com.storepc.repositories;

import com.storepc.models.address.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByRegionName(String regionName);

    boolean existsByRegionName(String regionName);

}