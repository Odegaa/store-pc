package com.storepc.payloads;

import com.storepc.models.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Long addressId;
    private List<Long> cardId;
}