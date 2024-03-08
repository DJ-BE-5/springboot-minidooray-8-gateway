package com.nhnacademy.edu.springboot.minidooray.gateway.repository;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.AccountDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDTORepository extends CrudRepository<AccountDTO, String> {
    //redis 리포지토리
}
