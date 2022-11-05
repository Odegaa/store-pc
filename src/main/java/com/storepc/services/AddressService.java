package com.storepc.services;

import com.storepc.models.address.Address;
import com.storepc.payloads.AddressDto;
import com.storepc.templates.Result;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    Address getAddress(Long addressId);

    Result addAddress(AddressDto addressDto);

    Result updateAddress(Long addressId, AddressDto addressDto);

    Result deleteAddress(Long addressId);

}
