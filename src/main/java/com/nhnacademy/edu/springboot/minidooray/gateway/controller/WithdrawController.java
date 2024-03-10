package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.UserDeleteRequestDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WithdrawController {
    private final GatewayAccountService accountService;

    public WithdrawController(GatewayAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/withdraw")
    public String signup() {
        return "withdraw_form";
    }

    @PostMapping("/user/withdraw")
    public String signupPost(@RequestBody UserDeleteRequestDTO userDeleteRequest,
                             Model model) {
        accountService.userDeleteRequest(userDeleteRequest);
        model.addAttribute("id", userDeleteRequest.getId());
        return "withdraw_success_form";
    }
}
