package com.nhnacademy.edu.springboot.minidooray.gateway.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Valid
public class UserRegisterRequestDTO {
    @NotNull
    @Length(min = 4, max = 18)
    private String id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 4)
    private String password;
}
