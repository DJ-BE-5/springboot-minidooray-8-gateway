package com.nhnacademy.edu.springboot.minidooray.gateway.config;


import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.UserAuthCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor());
        registry.addInterceptor(new UserAuthCheckInterceptor(
                new GatewayProjectService(
                        new RestTemplate()
                )
        ));
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.ofSeconds(5L))
                .setConnectTimeout(Duration.ofSeconds(3L))
                .build();
    }
}
