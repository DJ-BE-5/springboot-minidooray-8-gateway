package com.nhnacademy.edu.springboot.minidooray.gateway.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProjectServiceException extends RuntimeException{
    public ProjectServiceException(String message) {
        super(message);
    }
}
