package com.storepc.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegionDto {
    private String regionName;
    private Long cityId;
}