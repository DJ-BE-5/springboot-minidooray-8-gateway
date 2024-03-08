package com.nhnacademy.edu.springboot.minidooray.gateway.config;


import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.UserAuthCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor());
        registry.addInterceptor(new UserAuthCheckInterceptor());
    }
}
