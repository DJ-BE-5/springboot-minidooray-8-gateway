package com.nhnacademy.edu.springboot.minidooray.gateway.repository;


import com.nhnacademy.edu.springboot.minidooray.gateway.domain.AccountIdDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    AccountIdDTO findAccountById(Long id);
}
