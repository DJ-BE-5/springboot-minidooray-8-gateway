package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.OpenApiResponseDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Account;
import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OpenApiService {
    private final RestTemplate restTemplate;

    public OpenApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OpenApiResponseDTO> getAccounts() {
        ParameterizedTypeReference<List<Account>> accountList = new ParameterizedTypeReference<>() {
        };
        ParameterizedTypeReference<List<Customer>> customerList = new ParameterizedTypeReference<>() {
        };

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Account>> exchangeAccount = restTemplate.exchange(
                "http://localhost:9090",
                HttpMethod.GET,
                requestEntity,
                accountList);

        ResponseEntity<List<Customer>> exchangeCustomer = restTemplate.exchange(
                "http://localhost:9999",
                HttpMethod.GET,
                requestEntity,
                customerList);
        List<OpenApiResponseDTO> response = new ArrayList<>();

        for (Account account : exchangeAccount.getBody()) {
            OpenApiResponseDTO dto = new OpenApiResponseDTO();
            dto.setId(account.getId());
            dto.setBalance(account.getBalance());
            dto.setNumber(account.getNumber());

            for (Customer customer : exchangeCustomer.getBody()) {
                if (Objects.equals(account.getCustomerId(), customer.getId())) {
                    dto.setCustomer(customer);
                }
            }
            response.add(dto);
        }

        return response;
    }
}
