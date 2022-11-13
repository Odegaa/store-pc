package com.storepc.services.impls;

import com.storepc.models.Attachment;
import com.storepc.models.AttachmentContent;
import com.storepc.repositories.AttachmentContentRepository;
import com.storepc.repositories.AttachmentRepository;
import com.storepc.services.AttachmentContentService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

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
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            String nameForSystem = UUID.randomUUID().toString();

            Attachment attachment = new Attachment(null, originalFilename, contentType, size, nameForSystem);

            assert originalFilename != null;
            nameForSystem += originalFilename.substring(originalFilename.lastIndexOf("."));
            attachmentRepository.save(attachment);

            try {
                byte[] bytes = file.getBytes();
                AttachmentContent attachmentContent = new AttachmentContent(null, bytes, attachment);
                attachmentContentRepository.save(attachmentContent);

                Path path = Paths.get("files/", nameForSystem);
                Files.copy(file.getInputStream(), path);
                return new Result("Attachment content saved!", true, HttpStatus.CREATED);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Result("An error occurred!", false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void getAttachmentContent(Long contentId, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(contentId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findById(attachment.getId());
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                byte[] bytes = attachmentContent.getBytes();

                response.setHeader("Content-Disposition",
                        "attachment; filename = \"" + attachment.getOriginalName() + "\"");
                response.setContentType(attachment.getContentType());
                try {
                    FileCopyUtils.copy(bytes, response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Result deleteAttachmentContent(Long contentId) {
        Optional<AttachmentContent> contentOptional = attachmentContentRepository.findById(contentId);
        if (contentOptional.isPresent()) {
            attachmentContentRepository.deleteById(contentId);
            return new Result("Content deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Content not found!", false, HttpStatus.NOT_FOUND);
    }
}
