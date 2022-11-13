package com.storepc.controllers;

import com.storepc.models.address.Address;
import com.storepc.payloads.AddressDto;
import com.storepc.services.AddressService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    @PostMapping
    private ResponseEntity<Result> addAddress(@Valid @RequestBody AddressDto addressDto) {
        Result result = service.addAddress(addressDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    private ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/{addressId}")
    private ResponseEntity<Address> getAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok(service.getAddress(addressId));
    }

    @PutMapping("/{addressId}")
    private ResponseEntity<Result> updateAddress(@PathVariable Long addressId,
                                                 @Valid @RequestBody AddressDto addressDto) {
        Result result = service.updateAddress(addressId, addressDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{addressId}")
    private ResponseEntity<Result> deleteAddress(@PathVariable Long addressId) {
        Result result = service.deleteAddress(addressId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}