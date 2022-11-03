package com.storepc.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String streetName;
    private Integer houseNumber;
    private Long regionId;
}