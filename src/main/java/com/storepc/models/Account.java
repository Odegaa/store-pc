package com.storepc.models;

import com.storepc.models.address.Address;
import com.storepc.templates.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, message = "Minimum size firstname is - 3")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "Minimum size lastname is - 3")
    private String lastName;

    @NotNull
    @Column(unique = true)
    @Size(min = 5, message = "Minimum size username is - 5")
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 9, message = "Minimum size phone is - 9")
    private String phone;

    @NotNull
    @Size(min = 8, message = "Min size password is - 8")
    private String password;

    @NotNull
    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @OneToMany
    @ToString.Exclude
    private List<Card> cards;

}
