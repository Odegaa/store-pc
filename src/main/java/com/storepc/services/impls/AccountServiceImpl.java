package com.storepc.services.impls;

import com.storepc.models.Account;
import com.storepc.payloads.AccountDto;
import com.storepc.repositories.AccountRepository;
import com.storepc.repositories.AddressRepository;
import com.storepc.repositories.CardRepository;
import com.storepc.services.AccountService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              CardRepository cardRepository,
                              AddressRepository addressRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long accountId) {
        return null;
    }

    @Override
    public Result addAccount(AccountDto accountDto) {
        return null;
    }

    @Override
    public Result updateAccount(Long accountId, Account account) {
        return null;
    }

    @Override
    public Result deleteAccount(Long accountId) {
        return null;
    }
}