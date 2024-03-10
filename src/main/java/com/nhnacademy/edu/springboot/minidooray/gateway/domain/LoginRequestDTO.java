package com.nhnacademy.edu.springboot.minidooray.gateway.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Valid
public class LoginRequestDTO {
    @NotNull
    @Length(min = 4, max = 18)
    private String id;

    @NotNull
    @Length(min = 4)
    private String password;
}
