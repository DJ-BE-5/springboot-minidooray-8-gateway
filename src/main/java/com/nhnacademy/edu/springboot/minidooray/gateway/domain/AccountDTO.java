package com.nhnacademy.edu.springboot.minidooray.gateway.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "refresh_token")
@Valid
public class AccountDTO {
    @Id
    private String id;
    private String password;
}
