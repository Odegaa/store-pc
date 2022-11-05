package com.storepc.services.impls;

import com.storepc.models.address.Address;
import com.storepc.payloads.AddressDto;
import com.storepc.repositories.AddressRepository;
import com.storepc.repositories.RegionRepository;
import com.storepc.services.AddressService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, RegionRepository regionRepository) {
        this.addressRepository = addressRepository;
        this.regionRepository = regionRepository;
    }


    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddress(Long addressId) {
        return null;
    }

    @Override
    public Result addAddress(AddressDto addressDto) {
        return null;
    }

    @Override
    public Result updateAddress(Long addressId, AddressDto addressDto) {
        return null;
    }

    @Override
    public Result deleteAddress(Long addressId) {
        return null;
    }
}