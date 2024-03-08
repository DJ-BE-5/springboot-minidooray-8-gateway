package com.nhnacademy.edu.springboot.minidooray.gateway.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> basicRuntimeExceptionHandler() {
        return ResponseEntity
                .notFound()
                .build();
    }
}
