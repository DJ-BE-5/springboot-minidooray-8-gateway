package com.nhnacademy.edu.springboot.minidooray.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Member {
    @Id
    private String id;
    private String email;
    private String password;
    private String status;
}
