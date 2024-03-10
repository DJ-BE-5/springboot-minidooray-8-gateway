package com.nhnacademy.edu.springboot.minidooray.gateway.config;


import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.UserAuthCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/user/login.test");
        registry.addInterceptor(new UserAuthCheckInterceptor(new GatewayProjectService(new RestTemplate())))
                .addPathPatterns("/hello/*","projects/*");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5L * 1000))
                .setReadTimeout(Duration.ofMillis(5L * 1000))
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .build();
    }
}
