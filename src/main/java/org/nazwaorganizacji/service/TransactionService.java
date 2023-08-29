package org.nazwaorganizacji.service;

import lombok.RequiredArgsConstructor;
import org.nazwaorganizacji.DTO.TransactionRequest;
import org.nazwaorganizacji.repository.TransactionRepository;
import org.nazwaorganizacji.repository.entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;
//    jeśli coś jest statyczne to mozna to zamockowac w ten sposob ze tworzysz klase ktora ci to zwraca
//    private final TimeUtils timeUtils;

    public void createTransaction(TransactionRequest request) {
        accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );
        repository.save(
                Transaction
                        .builder()
                        .amount(request.getAmount())
                        .currency(request.getCurrency())
                        .fromAccountId(request.getFromAccountId())
                        .toAccountId(request.getToAccountId())
                        .transactionDate(
                                OffsetDateTime.now()
                        )
                        .build()
        );
    }
}
