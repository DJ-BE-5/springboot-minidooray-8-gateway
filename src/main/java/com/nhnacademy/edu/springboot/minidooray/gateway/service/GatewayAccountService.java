package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.User;
import org.springframework.stereotype.Service;

@Service
public class GatewayAccountService {

    //사용자 검색 요청
    public boolean userExistsRequest(String id) {
        return false;
    }

    //회원가입 요청
    public void userCreateRequest(User user) {

    }
}
