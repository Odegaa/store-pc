package com.storepc.controllers;

import com.storepc.models.Attachment;
import com.storepc.services.AttachmentService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService service;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('create')")
    private ResponseEntity<Result> addAttachment(@Valid @RequestBody Attachment attachment) {
        Result result = service.addAttachment(attachment);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<List<Attachment>> getAllAttachments() {
        return ResponseEntity.ok(service.getAllAttachments());
    }

    @GetMapping("/{attachmentId}")
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<Attachment> getAttachment(@PathVariable Long attachmentId) {
        return ResponseEntity.ok(service.getAttachment(attachmentId));
    }

    @PutMapping("/{attachmentId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> updateAttachment(@PathVariable Long attachmentId,
                                                    @Valid @RequestBody Attachment attachment) {
        Result result = service.updateAttachment(attachmentId, attachment);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{attachmentId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> deleteAttachment(@PathVariable Long attachmentId) {
        Result result = service.deleteAttachment(attachmentId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}