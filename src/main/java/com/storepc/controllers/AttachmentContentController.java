package com.storepc.controllers;

import com.storepc.services.AttachmentContentService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/attachmentContent")
public class AttachmentContentController {

    private final AttachmentContentService service;

    @PostMapping
    private ResponseEntity<Result> addAttachmentContent(MultipartHttpServletRequest request) {
        Result result = service.addAttachmentContent(request);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/{contentId}")
    private void getAttachmentContent(@PathVariable Long contentId, HttpServletResponse response) {
        service.getAttachmentContent(contentId, response);
    }

    @DeleteMapping("/{contentId}")
    private ResponseEntity<Result> deleteAttachmentContent(@PathVariable Long contentId) {
        Result result = service.deleteAttachmentContent(contentId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}