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
    @Column(unique = true)
    private String originalName;

    @NotNull
    private String contentType;

    @NotNull
    private Long size;

    @NotNull
    @Column(unique = true)
    private String generationName;

}
