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
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, name = "original_name")
    private String originalName;

    @NotNull
    @Column(name = "content_type")
    private String contentType;

    @NotNull
    @Column(name = "size")
    private Long size;

    @NotNull
    @Column(name = "generation_name", unique = true)
    private String generationName;

}
