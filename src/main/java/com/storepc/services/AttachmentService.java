package com.storepc.services;

import com.storepc.models.Attachment;
import com.storepc.templates.Result;

import java.util.List;

public interface AttachmentService {

    List<Attachment> getAllAttachments();

    Attachment getAttachment(Long attachmentId);

    Result addAttachment(Attachment attachment);

    Result updateAttachment(Long attachmentId, Attachment attachment);

    Result deleteAttachment(Long attachmentId);

}
