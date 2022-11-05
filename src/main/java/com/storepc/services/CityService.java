package com.storepc.services;

import com.storepc.models.address.City;
import com.storepc.payloads.CityDto;
import com.storepc.templates.Result;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City getCity(Long cityId);

    Result addCity(CityDto cityDto);

    Result updateCity(Long cityId, CityDto cityDto);

    Result deleteCity(Long cityId);

}
