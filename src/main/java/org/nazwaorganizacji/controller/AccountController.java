package org.nazwaorganizacji.controller;

import org.nazwaorganizacji.DTO.AccountRequest;
import org.nazwaorganizacji.DTO.AccountResponse;
import org.nazwaorganizacji.repository.entity.Account;
import org.nazwaorganizacji.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {
    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<AccountResponse> findById(@RequestParam Long id) {
        AccountResponse account = service.findById(id);
        return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createAccount(@RequestBody AccountRequest account) {
        service.save(account);
    }
}
