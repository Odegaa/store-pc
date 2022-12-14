package com.storepc.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String productName;

    @NotNull
    private String about;

    @NotNull
    private BigDecimal price;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @OneToMany
    @ToString.Exclude
    private List<Attachment> attachments;

    @NotNull
    @Column(unique = true, name = "code_for_product")
    private String codeForProduct;

}
