package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.minidooray.gateway.advice.GlobalExceptionHandler;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GatewayAccountServiceTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    @InjectMocks
    GatewayAccountService accountService;

    HttpHeaders httpHeaders;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(new GatewayAccountService())
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        ReflectionTestUtils.setField(accountService,
                "url",
                "http://localhost:9090");
    }

    @Test
    void userExistsRequest() {
        UserExistsRequestDTO requestDTO = new UserExistsRequestDTO();
        requestDTO.setUserId("testuser");

        HttpEntity<UserExistsRequestDTO> requestEntity = new HttpEntity<>(requestDTO, httpHeaders);

        UserExistsResponseDTO responseDTO = new UserExistsResponseDTO();
        responseDTO.setUserId("testuser");
        responseDTO.setExists(Boolean.TRUE);

        ResponseEntity<UserExistsResponseDTO> response =
                new ResponseEntity<>(responseDTO, HttpStatus.OK);

        when(restTemplate.exchange(
                "http://localhost:9090/user/testuser",
                HttpMethod.GET,
                requestEntity,
                UserExistsResponseDTO.class))
                .thenReturn(response);

        Boolean result = accountService.userExistsRequest("testuser");
        Assertions.assertTrue(result);
    }

    @Test
    void userCreateRequest() {
        UserRegisterRequestDTO requestDTO = new UserRegisterRequestDTO();
        requestDTO.setId("testuser");
        requestDTO.setEmail("test@asd.com");
        requestDTO.setPassword("asd123");
        HttpEntity<UserRegisterRequestDTO> requestEntity = new HttpEntity<>(requestDTO, httpHeaders);

        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
        responseDTO.setUserId("testuser");
        responseDTO.setSuccess(Boolean.TRUE);

        ResponseEntity<UserRegisterResponseDTO> response =
                new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        when(restTemplate.exchange(
                "http://localhost:9090/user",
                HttpMethod.POST,
                requestEntity,
                UserRegisterResponseDTO.class))
                .thenReturn(response);

        ResponseEntity<UserRegisterResponseDTO> result = accountService.userCreateRequest(requestDTO);
        Assertions.assertEquals(response, result);
    }

    @Test
    void loginRequest() {
        LoginRequestDTO requestDTO = new LoginRequestDTO();
        requestDTO.setId("testuser");
        requestDTO.setPassword("asd123");

        HttpEntity<LoginRequestDTO> requestEntity = new HttpEntity<>(requestDTO, httpHeaders);


        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setId("testuser");
        responseDTO.setPassword("asd123");
        responseDTO.setStatus("active");

        ResponseEntity<LoginResponseDTO> response =
                new ResponseEntity<>(responseDTO, HttpStatus.OK);

        when(restTemplate.exchange(
                "http://localhost:9090/user/login/testuser",
                HttpMethod.POST,
                requestEntity,
                LoginResponseDTO.class))
                .thenReturn(response);

        LoginResponseDTO result = accountService.loginRequest(requestDTO);
        Assertions.assertEquals("asd123", result.getPassword());
    }

    @Test
    void userDeleteRequest() {
        UserDeleteRequestDTO requestDTO = new UserDeleteRequestDTO();
        requestDTO.setId("testuser");
        requestDTO.setPassword("qwe123");

        HttpEntity<UserDeleteRequestDTO> requestEntity = new HttpEntity<>(requestDTO, httpHeaders);

        ResponseEntity<String> response =
                new ResponseEntity<>("", HttpStatus.NO_CONTENT);

        when(restTemplate.exchange(
                "http://localhost:9090/user/testuser",
                HttpMethod.DELETE,
                requestEntity,
                String.class))

                .thenReturn(response);

        accountService.userDeleteRequest(requestDTO);
        Assertions.assertDoesNotThrow(() -> accountService.userDeleteRequest(requestDTO));
    }
}