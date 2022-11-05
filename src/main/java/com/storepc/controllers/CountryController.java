package com.storepc.controllers;

import com.storepc.models.address.Country;
import com.storepc.services.CountryService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("country")
public class CountryController {

    private final CountryService service;

    @PostMapping
    private ResponseEntity<Result> addCountry(@Valid @RequestBody Country country) {
        Result result = service.addCountry(country);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    private ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(service.getAllCountry());
    }

    @GetMapping("/{countryId}")
    private ResponseEntity<Country> getCountry(@PathVariable Long countryId) {
        return ResponseEntity.ok(service.getCountry(countryId));
    }

    @PutMapping("/{countryId}")
    private ResponseEntity<Result> updateCountry(@PathVariable Long countryId,
                                                 @Valid @RequestBody Country country) {
        Result result = service.updateCountry(countryId, country);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{countryId}")
    private ResponseEntity<Result> deleteCountry(@PathVariable Long countryId) {
        Result result = service.deleteCountry(countryId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}