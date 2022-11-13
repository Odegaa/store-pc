package com.storepc.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attachment_content")
public class AttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private byte[] bytes;

    @NotNull
    @OneToOne
    private Attachment attachment;

}
