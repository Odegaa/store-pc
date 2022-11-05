package com.storepc.services.impls;

import com.storepc.repositories.AttachmentContentRepository;
import com.storepc.repositories.AttachmentRepository;
import com.storepc.services.AttachmentContentService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@Service
public class AttachmentContentServiceImpl implements AttachmentContentService {
    private final AttachmentContentRepository attachmentContentRepository;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentContentServiceImpl(AttachmentContentRepository attachmentContentRepository,
                                 AttachmentRepository attachmentRepository) {
        this.attachmentContentRepository = attachmentContentRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Result addAttachmentContent(MultipartHttpServletRequest request) {
        return null;
    }

    @Override
    public void getAttachmentContent(Long contentId, HttpServletResponse response) {
        attachmentContentRepository.findAll();
        attachmentRepository.findAll();
    }

    @Override
    public Result deleteAttachmentContent(Long contentId) {
        return null;
    }
}
