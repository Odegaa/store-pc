package com.storepc.services.impls;

import com.storepc.models.Attachment;
import com.storepc.repositories.AttachmentRepository;
import com.storepc.services.AttachmentService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment getAttachment(Long attachmentId) {
        return attachmentRepository.findById(attachmentId).orElse(null);
    }

    @Override
    public Result addAttachment(Attachment attachment) {
        try {
            boolean existsByGenerationName = attachmentRepository.existsByGenerationName(attachment.getGenerationName());
            if (existsByGenerationName) {
                return new Result("Generation names conflict", false, HttpStatus.CONFLICT);
            }
            Attachment newAttachment = new Attachment();
            newAttachment.setOriginalName(attachment.getOriginalName());
            newAttachment.setSize(attachment.getSize());
            newAttachment.setContentType(attachment.getContentType());
            newAttachment.setGenerationName(UUID.randomUUID().toString());
            attachmentRepository.save(newAttachment);
            return new Result("Attachment saved!", true, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("An error occurred!", false, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public Result updateAttachment(Long attachmentId, Attachment attachment) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (optionalAttachment.isPresent()) {
            Attachment updatingAttachment = optionalAttachment.get();
            updatingAttachment.setOriginalName(attachment.getOriginalName());
            updatingAttachment.setSize(attachment.getSize());
            updatingAttachment.setContentType(attachment.getContentType());
            updatingAttachment.setGenerationName(UUID.randomUUID().toString());
            attachmentRepository.save(updatingAttachment);
            return new Result("Attachment updated!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Attachment not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteAttachment(Long attachmentId) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (optionalAttachment.isPresent()) {
            attachmentRepository.deleteById(attachmentId);
            return new Result("Attachment deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Attachment not found!", false, HttpStatus.NOT_FOUND);
    }
}