package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.LoginRequestDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {
    private final GatewayAccountService accountService;
    ObjectMapper objectMapper;

    @Autowired
    public LoginController(GatewayAccountService accountService) {
        this.accountService = accountService;
        objectMapper = new ObjectMapper();
    }

    @GetMapping("/user/login")
    public String signup() {
        return "loginform";
    }

    @PostMapping("/user/login")
    public String signupPost(@RequestBody LoginRequestDTO loginRequest) {
        accountService.userExistsRequest(null);
        return "loginform";
    }
}
