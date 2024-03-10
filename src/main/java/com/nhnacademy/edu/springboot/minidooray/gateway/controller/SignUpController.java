package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.UserRegisterRequestDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.UserRegisterResponseDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignUpController {
    private final GatewayAccountService accountService;

    @Autowired
    public SignUpController(GatewayAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "signup_form";
    }

    @PostMapping("/user/signup")
    public String signupPost(@RequestBody UserRegisterRequestDTO userRegisterRequest,
                             Model model) {
        UserRegisterResponseDTO response = accountService.userCreateRequest(userRegisterRequest).getBody();
        model.addAttribute("id", response.getUserId());
        return "signup_success_form";
    }
}
