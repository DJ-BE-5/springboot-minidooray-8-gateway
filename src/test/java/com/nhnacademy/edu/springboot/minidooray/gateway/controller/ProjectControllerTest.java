package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.advice.GlobalExceptionHandler;
import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ProjectControllerTest {


}