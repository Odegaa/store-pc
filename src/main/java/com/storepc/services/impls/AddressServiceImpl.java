package com.storepc.services.impls;

import com.storepc.models.address.Address;
import com.storepc.models.address.Region;
import com.storepc.payloads.AddressDto;
import com.storepc.repositories.AddressRepository;
import com.storepc.repositories.RegionRepository;
import com.storepc.services.AddressService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public Result addAddress(AddressDto addressDto) {
        boolean byStreetName = addressRepository.existsByStreetName(addressDto.getStreetName());
        if (byStreetName) {
            return new Result("This street name already exist!", false, HttpStatus.CONFLICT);
        }
        Address address = new Address();
        address.setStreetName(addressDto.getStreetName());
        address.setHouseNumber(addressDto.getHouseNumber());
        Optional<Region> optionalRegion = regionRepository.findById(addressDto.getRegionId());
        if (optionalRegion.isPresent()) {
            address.setRegion(optionalRegion.get());
            addressRepository.save(address);
            return new Result("Address saved!", true, HttpStatus.CREATED);
        }
        return new Result("Region not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result updateAddress(Long addressId, AddressDto addressDto) {
        Optional<Address> optional = addressRepository.findById(addressId);
        if (optional.isPresent()) {
            Address address = optional.get();
            address.setStreetName(addressDto.getStreetName());
            address.setHouseNumber(addressDto.getHouseNumber());
            Optional<Region> optionalRegion = regionRepository.findById(addressDto.getRegionId());
            if (optionalRegion.isPresent()) {
                address.setRegion(optionalRegion.get());
                addressRepository.save(address);
                return new Result("Address updated", true, HttpStatus.ACCEPTED);
            }
        }
        return new Result("Address not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteAddress(Long addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            addressRepository.deleteById(addressId);
            return new Result("Address deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Address not found!", false, HttpStatus.NOT_FOUND);
    }
}