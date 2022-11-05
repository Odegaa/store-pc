package com.storepc.services.impls;

import com.storepc.models.address.City;
import com.storepc.models.address.Region;
import com.storepc.payloads.RegionDto;
import com.storepc.repositories.CityRepository;
import com.storepc.repositories.RegionRepository;
import com.storepc.services.RegionService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private static final String CREATED = "CREATED!";
    private static final String UPDATED = "UPDATED!";
    private static final String NOT_FOUND = "NOT_FOUND!";
    private static final String CONFLICT = "CONFLICT!";
    private static final String DELETED = "DELETED!";

    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository, CityRepository cityRepository) {
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegion(Long regionId) {
        return regionRepository.findById(regionId).orElse(null);
    }

    @Override
    public Result addRegion(RegionDto regionDto) {
        boolean existsByRegionName = regionRepository.existsByRegionName(regionDto.getRegionName());
        if (existsByRegionName) {
            return new Result(CONFLICT, false, HttpStatus.CONFLICT);
        }
        Region region = new Region();
        region.setRegionName(region.getRegionName());

        Optional<City> optionalCity = cityRepository.findById(regionDto.getCityId());
        if (optionalCity.isPresent()) {
            region.setCity(optionalCity.get());
            regionRepository.save(region);
            return new Result(CREATED, true, HttpStatus.CREATED);
        }
        return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result updateRegion(Long regionId, RegionDto regionDto) {
        Optional<Region> optional = regionRepository.findById(regionId);
        if (optional.isPresent()) {
            Region region = optional.get();
            region.setRegionName(regionDto.getRegionName());
            Optional<City> cityOptional = cityRepository.findById(regionDto.getCityId());
            if (cityOptional.isPresent()) {
                region.setCity(cityOptional.get());
                regionRepository.save(region);
                return new Result(UPDATED, true, HttpStatus.ACCEPTED);
            }
            return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
        }
        return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteRegion(Long regionId) {
        Optional<Region> regionOptional = regionRepository.findById(regionId);
        if (regionOptional.isPresent()) {
            regionRepository.deleteById(regionId);
            return new Result(DELETED, true, HttpStatus.ACCEPTED);
        }
        return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
    }
}
