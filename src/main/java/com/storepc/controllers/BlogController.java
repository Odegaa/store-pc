package com.storepc.controllers;

import com.storepc.models.Blog;
import com.storepc.payloads.BlogDto;
import com.storepc.services.BlogService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService service;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('create')")
    private ResponseEntity<Result> addBlog(@Valid @RequestBody BlogDto blogDto) {
        Result result = service.addBlog(blogDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(service.getAllBlogs());
    }

    @GetMapping("/{blogId}")
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<Blog> getBlog(@PathVariable Long blogId) {
        return ResponseEntity.ok(service.getBlog(blogId));
    }

    @PutMapping("/{blogId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> updateBlog(@PathVariable Long blogId,
                                              @Valid @RequestBody BlogDto blogDto) {
        Result result = service.updateBlog(blogId, blogDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{blogId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> deleteBlog(@PathVariable Long blogId) {
        Result result = service.deleteBlog(blogId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}