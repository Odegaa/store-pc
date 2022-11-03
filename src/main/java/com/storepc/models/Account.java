package com.storepc.models;

import com.storepc.models.address.Address;
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
    @Column(name = "first_name")
    @Size(min = 3, message = "Minimum size firstname is - 3")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 3, message = "Minimum size lastname is - 3")
    private String lastName;

    @NotNull
    @Column(unique = true, name = "username")
    @Size(min = 5, message = "Minimum size username is - 5")
    private String username;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone_number")
    @Size(min = 9, message = "Minimum size phone is - 9")
    private String phone;

    @NotNull
    @Column(name = "password")
    @Size(min = 8, message = "Min size password is - 8")
    private String password;

    @NotNull
    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull
    @OneToMany
    private List<Card> cards;

}
