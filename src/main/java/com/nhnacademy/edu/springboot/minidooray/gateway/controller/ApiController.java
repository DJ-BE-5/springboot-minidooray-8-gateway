package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.AccountIdDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.OpenApiResponseDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Account;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@ConfigurationProperties(prefix = "my.openapi.port")
public class ApiController {
    private String account;
    private String customer;
    private String url;
    private final RestTemplate restTemplate;
    private static ParameterizedTypeReference<List<OpenApiResponseDTO>> accountlist = new ParameterizedTypeReference<>() {
    };

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        url = "http://localhost:";
    }

    @GetMapping("/accounts")
    public List<OpenApiResponseDTO> getAccounts() {

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<List<OpenApiResponseDTO>> exchange = restTemplate.exchange(
//                url + account,
//                HttpMethod.GET,
//                requestEntity,
//                accountlist);

        if (exchange.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException();

        return exchange.getBody();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Long id) {
        return null; //accountService.getAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountIdDTO> createAccount(@RequestBody Account account) {
        try {
            //accountService.createAccount(account);
        } catch (RuntimeException e) {
            ResponseEntity.internalServerError().build();
        }
        return null;// ResponseEntity.status(HttpStatus.CREATED).body(accountService.getAccountIdDTO(account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
//
//        if (accountService.exists(id)) {
//            try {
//                accountService.deleteAccount(id);
//            } catch (RuntimeException e) {
//                return ResponseEntity.internalServerError().build();
//            }
//            accountService.deleteAccount(id);
//            return ResponseEntity.ok().build();
//        } else
//            return ResponseEntity.notFound().build();
        return null;

    }
}
