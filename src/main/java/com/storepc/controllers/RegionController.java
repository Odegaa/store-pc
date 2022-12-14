package com.storepc.controllers;

import com.storepc.models.address.Region;
import com.storepc.payloads.RegionDto;
import com.storepc.services.RegionService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/region")
public class RegionController {

    private final RegionService service;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('create')")
    private ResponseEntity<Result> addRegion(@Valid @RequestBody RegionDto regionDto) {
        Result result = service.addRegion(regionDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(service.getAllRegions());
    }

    @GetMapping("/{regionId}")
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<Region> getRegion(@PathVariable Long regionId) {
        return ResponseEntity.ok(service.getRegion(regionId));
    }

    @PutMapping("/{regionId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> updateRegion(@PathVariable Long regionId,
                                                @Valid @RequestBody RegionDto regionDto) {
        Result result = service.updateRegion(regionId, regionDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{regionId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> deleteRegion(@PathVariable Long regionId) {
        Result result = service.deleteRegion(regionId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}