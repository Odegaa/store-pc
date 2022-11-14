package com.storepc.controllers;

import com.storepc.models.Account;
import com.storepc.payloads.AccountDto;
import com.storepc.services.AccountService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    @PostMapping
    private ResponseEntity<Result> addAccount(@Valid @RequestBody AccountDto accountDto) {
        Result result = service.addAccount(accountDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    private ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/{accountId}")
    private ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(service.getAccount(accountId));
    }

    @PutMapping("/{accountId}")
    private ResponseEntity<Result> updateAccount(@PathVariable Long accountId,
                                                 @Valid @RequestBody AccountDto accountDto) {
        Result result = service.updateAccount(accountId, accountDto);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{accountId}")
    private ResponseEntity<Result> deleteAccount(@PathVariable Long accountId) {
        Result result = service.deleteAccount(accountId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

}