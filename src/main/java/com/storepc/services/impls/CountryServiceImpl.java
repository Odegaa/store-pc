package com.storepc.services.impls;

import com.storepc.models.address.Country;
import com.storepc.repositories.CountryRepository;
import com.storepc.services.CountryService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountry(Long countryId) {
        return countryRepository.findById(countryId).orElse(null);
    }

    @Override
    public Result addCountry(Country country) {
        boolean byCountryName = countryRepository.existsByCountryName(country.getCountryName());
        if (byCountryName) {
            return new Result("This country name already exist!", false, HttpStatus.CONFLICT);
        }
        Country newCountry = new Country();
        newCountry.setCountryName(country.getCountryName());
        countryRepository.save(newCountry);
        return new Result("Country saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateCountry(Long countryId, Country country) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isPresent()) {
            boolean byCountryName = countryRepository.existsByCountryName(country.getCountryName());
            if (byCountryName) {
                return new Result("This country name already exist!", false, HttpStatus.CONFLICT);
            }
            countryRepository.save(country);
            return new Result("Country updated!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Country not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteCountry(Long countryId) {
        Optional<Country> countryOptional = countryRepository.findById(countryId);
        if (countryOptional.isPresent()) {
            countryRepository.deleteById(countryId);
            return new Result("Country deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Country not found!", false, HttpStatus.NOT_FOUND);
    }
}