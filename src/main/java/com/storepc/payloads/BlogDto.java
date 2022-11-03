package com.storepc.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogDto {
    private String title;
    private String text;
    private Long accountId;
}