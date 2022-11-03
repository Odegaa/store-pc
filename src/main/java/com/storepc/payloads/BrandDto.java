package com.storepc.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BrandDto {
    private String brandName;
    private String about;
    private List<Long> productId;
}