package com.nhnacademy.edu.springboot.minidooray.gateway.controller;


import com.nhnacademy.edu.springboot.minidooray.gateway.domain.AccountIdDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Account;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.AccountClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private final AccountClientService accountService;

    @Autowired
    public AccountController(AccountClientService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountIdDTO> createAccount(@RequestBody Account account) {
        try {
            accountService.createAccount(account);
        } catch (RuntimeException e) {
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.getAccountIdDTO(account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {

        if (accountService.exists(id)) {
            try {
                accountService.deleteAccount(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }

}
