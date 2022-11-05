package com.storepc.templates;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Result {
    private String message;
    private boolean success;
    private HttpStatus status;
}
