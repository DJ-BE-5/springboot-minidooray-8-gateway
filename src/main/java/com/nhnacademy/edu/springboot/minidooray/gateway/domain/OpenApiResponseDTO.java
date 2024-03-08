package com.nhnacademy.edu.springboot.minidooray.gateway.domain;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Customer;
import lombok.Data;

@Data
public class OpenApiResponseDTO {
    private Long id;
    private String number;
    private Integer balance;
    private Customer customer;
}
