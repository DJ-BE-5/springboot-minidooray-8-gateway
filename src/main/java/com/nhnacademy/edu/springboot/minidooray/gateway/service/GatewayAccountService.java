package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.*;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.DuplicateUserIdException;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.NoSuchUserException;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.PasswordNotMatchesException;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.SomethingWentWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GatewayAccountService {
    RestTemplate restTemplate;
    ObjectMapper objectMapper;
    @Value("${minidooray.api.url.account}")
    String url;

    @Autowired
    public GatewayAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        objectMapper = new ObjectMapper();
    }

    //사용자 검색 요청
    public boolean userExistsRequest(String id) {
        HttpEntity<UserExistsRequestDTO> requestEntity = new HttpEntity<>(new UserExistsRequestDTO(id), generateHttpJsonToJsonHeader());

        ResponseEntity<UserExistsResponseDTO> userExistsResponse = restTemplate.exchange(
                url,
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

        ResponseEntity<UserRegisterResponseDTO> userRegisterResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                UserRegisterResponseDTO.class);

        if (userRegisterResponse.getStatusCode() == HttpStatus.CREATED) {
            return userRegisterResponse;

        } else if (userRegisterResponse.getStatusCode() == HttpStatus.CONFLICT) {
            throw new DuplicateUserIdException();

        } else
            throw new SomethingWentWrongException();

    }

    // 로그인 요청
    public LoginResponseDTO loginRequest(LoginRequestDTO loginRequest) {

        HttpEntity<LoginRequestDTO> requestEntity = new HttpEntity<>(loginRequest, generateHttpJsonToJsonHeader());

        ResponseEntity<LoginResponseDTO> loginResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                LoginResponseDTO.class);

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            return loginResponse.getBody();

        } else if (loginResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NoSuchUserException();

        } else
            throw new PasswordNotMatchesException();

    }

    // 회원 삭제 요청
    public void userDeleteRequest(UserDeleteRequestDTO userDeleteRequest) {

        HttpEntity<?> requestEntity = new HttpEntity<>(userDeleteRequest, generateHttpJsonToJsonHeader());

        ResponseEntity<?> userDeleteResponse = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                String.class);

        if (userDeleteResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
            // do nothing
        } else if (userDeleteResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NoSuchUserException();

        } else
            throw new PasswordNotMatchesException();

    }

    private HttpHeaders generateHttpJsonToJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }

}
