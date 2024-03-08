package com.nhnacademy.edu.springboot.minidooray.gateway.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Account2")
public class Account {
    @Id
    private Long id;
    private String number;
    private Integer balance;
    private Long customerId;
}
