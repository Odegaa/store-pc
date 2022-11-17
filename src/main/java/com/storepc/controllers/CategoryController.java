package com.storepc.controllers;

import com.storepc.models.Category;
import com.storepc.payloads.CategoryDto;
import com.storepc.services.CategoryService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('create')")
    private ResponseEntity<Result> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Result result = service.addCategory(categoryDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.getCategory(categoryId));
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> updateCategory(@PathVariable Long categoryId,
                                                  @Valid @RequestBody CategoryDto categoryDto) {
        Result result = service.updateCategory(categoryId, categoryDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> deleteCategory(@PathVariable Long categoryId) {
        Result result = service.deleteCategory(categoryId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}