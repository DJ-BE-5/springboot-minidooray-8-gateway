package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Project;
import com.nhnacademy.edu.springboot.minidooray.request.ProjectRequest;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
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
    public boolean projectCreateRequest(ProjectRequest project) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProjectRequest> requestEntity = new HttpEntity<>(project, headers);

        ResponseEntity<?> response = restTemplate.exchange(
                "http://localhost:9999/projects",
                HttpMethod.POST,
                requestEntity,
                ProjectResponse.class
        );

        if(response.getStatusCode()!=HttpStatus.CREATED){
            return false;
        }
        return true;
    }
    //프로젝트 수정 요청
    public boolean projectDeleteRequest(Long projectId){
        ResponseEntity<?> response = restTemplate.exchange(
                "http://localhost:9999/projects/"+projectId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        if(response.getStatusCode()!=HttpStatus.NO_CONTENT){
            return false;
        }
        return true;
    }
}
