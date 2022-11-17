package com.storepc.controllers;

import com.storepc.models.Product;
import com.storepc.payloads.ProductDto;
import com.storepc.services.ProductService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('create')")
    private ResponseEntity<Result> addProduct(@Valid @RequestBody ProductDto productDto) {
        Result result = service.addProduct(productDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{productId}")
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getProduct(productId));
    }

    @PutMapping("/{productId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> updateProduct(@PathVariable Long productId,
                                                 @Valid @RequestBody ProductDto productDto) {
        Result result = service.updateProduct(productId, productDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> deleteProduct(@PathVariable Long productId) {
        Result result = service.deleteProduct(productId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}