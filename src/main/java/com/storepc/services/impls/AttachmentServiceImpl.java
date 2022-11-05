package com.storepc.services.impls;

import com.storepc.models.Attachment;
import com.storepc.repositories.AttachmentRepository;
import com.storepc.services.AttachmentService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Result addAttachment(Attachment attachment) {
        return null;
    }

    @Override
    public Result updateAttachment(Long attachmentId, Attachment attachment) {
        return null;
    }

    @Override
    public Result deleteAttachment(Long attachmentId) {
        return null;
    }
}