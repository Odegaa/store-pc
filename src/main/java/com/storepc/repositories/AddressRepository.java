package com.storepc.repositories;

import com.storepc.models.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByStreetName(String streetName);

    boolean existsByStreetName(String streetName);

}