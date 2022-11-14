package com.storepc.services.impls;

import com.storepc.models.Account;
import com.storepc.models.Card;
import com.storepc.payloads.AccountDto;
import com.storepc.repositories.AccountRepository;
import com.storepc.repositories.AddressRepository;
import com.storepc.repositories.CardRepository;
import com.storepc.services.AccountService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public Result addAccount(AccountDto accountDto) {
        boolean byUsername = accountRepository.existsByUsername(accountDto.getUsername());
        if (byUsername) {
            return new Result("This username already exist!", false, HttpStatus.CONFLICT);
        }

        boolean byEmail = accountRepository.existsByEmail(accountDto.getEmail());
        if (byEmail) {
            return new Result("This email already exist!", false, HttpStatus.CONFLICT);
        }

        boolean byPhone = accountRepository.existsByPhone(accountDto.getPhone());
        if (byPhone) {
            return new Result("This phone number already exist!", false, HttpStatus.CONFLICT);
        }

        Account account = new Account();
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEmail(accountDto.getEmail());
        account.setPhone(accountDto.getPhone());

        addressRepository.findById(accountDto.getAddressId()).ifPresent(account::setAddress);

        List<Card> cardList = new ArrayList<>();

        for (Long cards : accountDto.getCardId()) {
            Optional<Card> optionalCard = cardRepository.findById(cards);
            if (!optionalCard.isPresent()) {
                return new Result("This card not found!", false, HttpStatus.NOT_FOUND);
            }
            cardList.add(optionalCard.get());
        }
        account.setCards(cardList);
        accountRepository.save(account);
        return new Result("Account saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateAccount(Long accountId, AccountDto accountDto) {
        boolean byUsername = accountRepository.existsByUsername(accountDto.getUsername());
        if (byUsername) {
            return new Result("This username already exist!", false, HttpStatus.CONFLICT);
        }

        boolean byEmail = accountRepository.existsByEmail(accountDto.getEmail());
        if (byEmail) {
            return new Result("This email already exist!", false, HttpStatus.CONFLICT);
        }

        boolean byPhone = accountRepository.existsByPhone(accountDto.getPhone());
        if (byPhone) {
            return new Result("This phone number already exist!", false, HttpStatus.CONFLICT);
        }

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setFirstName(accountDto.getFirstName());
            account.setLastName(accountDto.getLastName());
            account.setUsername(accountDto.getUsername());
            account.setPassword(accountDto.getPassword());
            account.setEmail(accountDto.getEmail());
            account.setPhone(accountDto.getPhone());

            addressRepository.findById(accountDto.getAddressId()).ifPresent(account::setAddress);

            List<Card> cardList = new ArrayList<>();

            for (Long cards : accountDto.getCardId()) {
                Optional<Card> optionalCard = cardRepository.findById(cards);
                if (!optionalCard.isPresent()) {
                    return new Result("This card not found!", false, HttpStatus.NOT_FOUND);
                }
                cardList.add(optionalCard.get());
            }
            account.setCards(cardList);
            accountRepository.save(account);
            return new Result("Account updated!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Account not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(accountId);
            return new Result("Account deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Account not found!", false, HttpStatus.NOT_FOUND);
    }
}