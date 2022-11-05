package com.storepc.services;

import com.storepc.models.address.Country;
import com.storepc.templates.Result;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountry();

    Country getCountry(Long countryId);

    Result addCountry(Country country);

    Result updateCountry(Long countryId, Country country);

    Result deleteCountry(Long countryId);

}
