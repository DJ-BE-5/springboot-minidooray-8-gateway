package com.nhnacademy.edu.springboot.minidooray.gateway.serverproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("account")
@Component
public class AccountServerProperties implements ServerProperties {
    @Value("${my.openapi.port}")
    String port;

    @Override
    public String getPort() {
        return port;
    }
}
