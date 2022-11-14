package com.storepc.services;

import com.storepc.models.Account;
import com.storepc.payloads.AccountDto;
import com.storepc.templates.Result;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccount(Long accountId);

    Result addAccount(AccountDto accountDto);

    Result updateAccount(Long accountId, AccountDto accountDto);

    Result deleteAccount(Long accountId);
}
