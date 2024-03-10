package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.LoginRequestDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.LoginResponseDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController {
    private final GatewayAccountService accountService;
    ObjectMapper objectMapper;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Autowired
    public LoginController(GatewayAccountService accountService) {
        this.accountService = accountService;
        objectMapper = new ObjectMapper();
    }

    @GetMapping("user/login")
    public String signup() {
        return "login_form";
    }

    @PostMapping("user/login")
    public String signupPost(@RequestBody LoginRequestDTO loginRequest,
                             HttpServletRequest request) {

        LoginResponseDTO responseDTO = accountService.loginRequest(loginRequest);

        valueOperations.set("minidooray8_id", responseDTO.getId());
        request.getSession(true).setAttribute("id", responseDTO.getId());

        return "login_success_form";
    }

    @PostMapping("user/login.test")
    public String signuptestpost(@RequestBody LoginRequestDTO loginRequest,
                                 HttpServletRequest request) {

        LoginResponseDTO responseDTO = accountService.loginRequest(loginRequest);

        log.debug("{}", valueOperations.get("minidooray8_id"));
        return "login_form";
    }
}
