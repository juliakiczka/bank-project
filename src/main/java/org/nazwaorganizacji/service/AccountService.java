package org.nazwaorganizacji.service;

import lombok.RequiredArgsConstructor;
import org.nazwaorganizacji.DTO.AccountRequest;
import org.nazwaorganizacji.DTO.AccountResponse;
import org.nazwaorganizacji.repository.AccountRepository;
import org.nazwaorganizacji.repository.entity.Account;
import org.nazwaorganizacji.repository.entity.Client;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountResponse findById(final Long id) {
        return accountRepository
                .findById(id)
                .map(account -> AccountResponse.builder().balance(account.getBalance()).currency(account.getCurrency()).userId(account.getUserId()).build())
                .orElseThrow(() -> new IllegalArgumentException("Account with ID: " + id + " not found."));
    }

    public void save(final AccountRequest account) {
        accountRepository
                .save(Account
                        .builder()
                        .balance(account.getBalance())
                        .currency(account.getCurrency())
                        .userId(account.getUserId())
                        .build());
    }

    public void transfer(
            long fromAccountId,
            long toAccountId,
            double amount
    ) {
        validateAmount(amount);
        if (fromAccountId == toAccountId) {
            throw new IllegalArgumentException("fromAccountId and toAccountId can't be equal!");
        }
        Account fromAccount = accountRepository.getOne(fromAccountId);
        Account toAccount = accountRepository.getOne(toAccountId);
        if (fromAccount.getBalance() - amount >= 0) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
        } else {
            throw new NoSufficientFundsException("Not enough funds!");
        }
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }


//    public void withdraw(
//            final String email,
//            final double amount) {
//        validateAmount(amount);
//        if (Objects.isNull(email)) {
//            throw new IllegalArgumentException("Email cant be null!");
//        }
//        final String lowerCaseEmail = email.toLowerCase();
//        final Client client = findByEmail(lowerCaseEmail);
//        if (amount > client.getBalance()) {
//            throw new NoSufficientFundsException("Balance must be higher or equal then amount!");
//        }
//        final double newBalance = client.getBalance() - amount;
//        client.setBalance(newBalance);
//        clientRepository.save(client);
//    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
    }
}
