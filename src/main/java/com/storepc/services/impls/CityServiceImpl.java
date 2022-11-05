package com.storepc.services.impls;

import com.storepc.models.address.City;
import com.storepc.models.address.Country;
import com.storepc.payloads.CityDto;
import com.storepc.repositories.CityRepository;
import com.storepc.repositories.CountryRepository;
import com.storepc.services.CityService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private static final String CREATED = "CREATED!";
    private static final String UPDATED = "UPDATED!";
    private static final String NOT_FOUND = "NOT_FOUND!";
    private static final String CONFLICT = "CONFLICT!";
    private static final String DELETED = "DELETED!";


    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    @Override
    public Result addCity(CityDto cityDto) {
        boolean existsByCityName = cityRepository.existsByCityName(cityDto.getCityName());
        if (existsByCityName) {
            return new Result(CONFLICT, false, HttpStatus.CONFLICT);
        }
        City city = new City();
        city.setCityName(cityDto.getCityName());

        Optional<Country> optionalCountry = countryRepository.findById(cityDto.getCountryId());
        if (!optionalCountry.isPresent()) {
            return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
        }
        city.setCountry(optionalCountry.get());
        cityRepository.save(city);
        return new Result(CREATED, true, HttpStatus.CREATED);
    }

    @Override
    public Result updateCity(Long cityId, CityDto cityDto) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            city.setCityName(cityDto.getCityName());

            Optional<Country> optionalCountry = countryRepository.findById(cityDto.getCountryId());
            if (optionalCountry.isPresent()) {
                city.setCountry(optionalCountry.get());
                cityRepository.save(city);
                return new Result(UPDATED, true, HttpStatus.ACCEPTED);
            }
            return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
        }
        return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteCity(Long cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            cityRepository.deleteById(cityId);
            return new Result(DELETED, true, HttpStatus.ACCEPTED);
        }
        return new Result(NOT_FOUND, false, HttpStatus.NOT_FOUND);
    }
}
