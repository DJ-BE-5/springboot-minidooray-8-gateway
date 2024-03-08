package com.nhnacademy.edu.springboot.minidooray.gateway.config;

import lombok.Data;
import org.springframework.context.annotation.Profile;

import javax.validation.constraints.NotNull;

@Profile("!test")
@Data
public class AccountAdaptorPropertiesDefault implements AccountAdaptorProperties {
    @NotNull
    private String address;

    @Override
    public String getAddress() {
        return address;
    }
}
