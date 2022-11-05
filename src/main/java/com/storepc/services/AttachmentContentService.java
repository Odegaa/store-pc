package com.storepc.services;

import com.storepc.templates.Result;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentContentService {

    Result addAttachmentContent(MultipartHttpServletRequest request);

    void getAttachmentContent(Long contentId, HttpServletResponse response);

    Result deleteAttachmentContent(Long contentId);

}
