package com.storepc.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private String about;
    private BigDecimal price;
    private Long categoryId;
    private List<Long> attachmentId;
}