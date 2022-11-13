package com.storepc.models.address;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String streetName;

    @NotNull
    private Integer houseNumber;

    @NotNull
    @ManyToOne
    private Region region;

}
