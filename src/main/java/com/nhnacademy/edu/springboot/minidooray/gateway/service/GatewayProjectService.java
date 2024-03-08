package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Project;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GatewayProjectService {

    private final RestTemplate restTemplate;
    private static ParameterizedTypeReference REF = new ParameterizedTypeReference<List<Project>>(){};

    public GatewayProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //프로젝트 검색 요청
    public boolean projectExistsRequest(String id) {
        return false;
    }
    public List<Project> getProjects(){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Project>> exchange = restTemplate.exchange("localhost:9999/projects",
                HttpMethod.GET, requestEntity,REF
        );

        return exchange.getBody();
    }

    //프로젝트 생성 요청
    public boolean projectCreateRequest(Project project) {
        return false;
    }

    //프로젝트 수정 요청
    public boolean projectUpdateRequest(Project project) {
        return false;
    }
}
