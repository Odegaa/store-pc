package com.storepc.controllers;

import com.storepc.models.Brand;
import com.storepc.payloads.BrandDto;
import com.storepc.services.BrandService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final BrandService service;

    @PostMapping
    private ResponseEntity<Result> addBrand(@Valid @RequestBody BrandDto brandDto) {
        Result result = service.addBrand(brandDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    private ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(service.getAllBrands());
    }

    @GetMapping("/{brandId}")
    private ResponseEntity<Brand> getBrand(@PathVariable Long brandId) {
        return ResponseEntity.ok(service.getBrand(brandId));
    }

    @PutMapping("/{brandId}")
    private ResponseEntity<Result> updateBrand(@PathVariable Long brandId,
                                               @Valid @RequestBody BrandDto brandDto) {
        Result result = service.updateBrand(brandId, brandDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{brandId}")
    private ResponseEntity<Result> deleteBrand(@PathVariable Long brandId) {
        Result result = service.deleteBrand(brandId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}