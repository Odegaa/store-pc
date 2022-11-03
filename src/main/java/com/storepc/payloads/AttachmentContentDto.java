package com.storepc.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentContentDto {
    private byte[] bytes;
    private Long attachmentId;
}