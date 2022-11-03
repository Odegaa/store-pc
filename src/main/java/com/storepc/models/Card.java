package com.storepc.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, name = "card_number")
    @Size(min = 16, max = 16, message = "card number size 16.")
    private String cardNumber;

    @NotNull
    @Column(name = "card_user")
    private String cardUser;

    @NotNull
    @Column(name = "cvc")
    private String cvc;

}
