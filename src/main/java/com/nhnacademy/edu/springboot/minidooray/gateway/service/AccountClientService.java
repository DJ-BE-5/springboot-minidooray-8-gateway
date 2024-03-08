package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.AccountIdDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Account;
import com.nhnacademy.edu.springboot.minidooray.gateway.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountClientService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountClientService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return accountRepository.existsById(id);
    }

    public AccountIdDTO getAccountIdDTO(Account account) {
        return accountRepository.findAccountById(account.getId());
    }
}
