package com.storepc.controllers;

import com.storepc.models.address.City;
import com.storepc.payloads.CityDto;
import com.storepc.services.CityService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService service;

    @PostMapping
    private ResponseEntity<Result> addCity(@Valid @RequestBody CityDto cityDto) {
        Result result = service.addCity(cityDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    private ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(service.getAllCities());
    }

    @GetMapping("/{cityId}")
    private ResponseEntity<City> getCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(service.getCity(cityId));
    }

    @PutMapping("/{cityId}")
    private ResponseEntity<Result> updateCity(@PathVariable Long cityId,
                                              @Valid @RequestBody CityDto cityDto) {
        Result result = service.updateCity(cityId, cityDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{cityId}")
    private ResponseEntity<Result> deleteCity(@PathVariable Long cityId) {
        Result result = service.deleteCity(cityId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}