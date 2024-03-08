package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.minidooray.gateway.advice.GlobalExceptionHandler;
import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    MockMvc mockMvc;
    GatewayAccountService accountService = Mockito.mock(GatewayAccountService.class);
    LoginCheckInterceptor loginCheckInterceptor = Mockito.mock(LoginCheckInterceptor.class);

    @MockBean(name = "redisTemplate")
    ValueOperations<String, String> valueOperations = Mockito.mock(ValueOperations.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new LoginController(accountService))
                .addInterceptors(loginCheckInterceptor)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();

        when(loginCheckInterceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    void signup() throws Exception {
        mockMvc.perform(get("/user/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void signupPost() throws Exception {
//        redis 테스트 불가
        // 가능하신 분이 해주세요.. 저는 못하겠습니다.
//        LoginRequestDTO requestDTO = new LoginRequestDTO();
//        requestDTO.setId("test");
//        requestDTO.setPassword("asd123");
//
//        LoginResponseDTO responseDTO = new LoginResponseDTO();
//        responseDTO.setId("test");
//        responseDTO.setPassword("asd123");
//        responseDTO.setStatus("active");
//
//        when(accountService.loginRequest(any())).thenReturn(responseDTO);
//        doNothing().when(valueOperations).set(anyString(), anyString());
//
//        HttpSession session = mockMvc
//                .perform(post("/user/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getRequest()
//                .getSession();
//
//        Assertions.assertEquals("test", session.getAttribute("id"));
    }
}