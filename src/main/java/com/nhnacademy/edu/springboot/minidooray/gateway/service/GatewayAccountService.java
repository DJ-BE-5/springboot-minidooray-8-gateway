package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.*;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.DuplicateUserIdException;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.NoSuchUserException;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.PasswordNotMatchesException;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.SomethingWentWrongException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

@Slf4j
@Service("accountService")
public class GatewayAccountService {
    RestTemplate restTemplate;
    ObjectMapper objectMapper;
    @Value("${minidooray.api.url.account}")
    String url;

    @Autowired
    public GatewayAccountService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5L * 1000))
                .setReadTimeout(Duration.ofMillis(5L * 1000))
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .build();
        objectMapper = new ObjectMapper();
    }

    //사용자 검색 요청
    public boolean userExistsRequest(String id) {
        HttpEntity<UserExistsRequestDTO> requestEntity = new HttpEntity<>(new UserExistsRequestDTO(id), generateHttpJsonToJsonHeader());

        ResponseEntity<UserExistsResponseDTO> userExistsResponse = restTemplate.exchange(
                url + "/user/" + id,
                HttpMethod.GET,
                requestEntity,
                UserExistsResponseDTO.class);

        if (userExistsResponse.getStatusCode() == HttpStatus.OK) {
            return userExistsResponse.getBody().getExists();

        } else
            throw new NoSuchUserException();

    }

    //회원가입 요청
    public ResponseEntity<UserRegisterResponseDTO> userCreateRequest(UserRegisterRequestDTO userRegisterRequest) {

        HttpEntity<UserRegisterRequestDTO> requestEntity = new HttpEntity<>(userRegisterRequest, generateHttpJsonToJsonHeader());
        try {
            ResponseEntity<UserRegisterResponseDTO> userRegisterResponse = restTemplate.exchange(
                    url + "/user",
                    HttpMethod.POST,
                    requestEntity,
                    UserRegisterResponseDTO.class);

            if (userRegisterResponse.getStatusCode() == HttpStatus.CREATED) {
                return userRegisterResponse;
            } else throw new SomethingWentWrongException();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus statusCode = e.getStatusCode();

            if (statusCode == HttpStatus.CONFLICT) {
                throw new DuplicateUserIdException();

            } else
                throw new SomethingWentWrongException();
        }
    }

    // 로그인 요청
    public LoginResponseDTO loginRequest(LoginRequestDTO loginRequest) {

        HttpEntity<LoginRequestDTO> requestEntity = new HttpEntity<>(loginRequest, generateHttpJsonToJsonHeader());

        try {
            ResponseEntity<LoginResponseDTO> loginResponse = restTemplate.exchange(
                    url + "/user/login/" + loginRequest.getId(),
                    HttpMethod.POST,
                    requestEntity,
                    LoginResponseDTO.class);

            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                return loginResponse.getBody();
            } else throw new SomethingWentWrongException();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus statusCode = e.getStatusCode();

            if (statusCode == HttpStatus.NOT_FOUND) {
                throw new NoSuchUserException();

            } else
                throw new PasswordNotMatchesException();
        }
    }

    // 회원 삭제 요청
    public void userDeleteRequest(UserDeleteRequestDTO userDeleteRequest) {

        HttpEntity<?> requestEntity = new HttpEntity<>(userDeleteRequest, generateHttpJsonToJsonHeader());

        try {
            ResponseEntity<?> userDeleteResponse = restTemplate.exchange(
                    url + "/user/" + userDeleteRequest.getId(),
                    HttpMethod.DELETE,
                    requestEntity,
                    String.class);

            if (userDeleteResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                // do nothing
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus statusCode = e.getStatusCode();

            if (statusCode == HttpStatus.NOT_FOUND) {
                throw new NoSuchUserException();

            } else
                throw new PasswordNotMatchesException();
        }
    }

    private HttpHeaders generateHttpJsonToJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }

}
