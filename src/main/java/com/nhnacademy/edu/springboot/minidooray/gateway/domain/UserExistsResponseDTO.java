package com.nhnacademy.edu.springboot.minidooray.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UserExistsResponseDTO {
    String userId;
    @NotNull
    Boolean exists;
}
