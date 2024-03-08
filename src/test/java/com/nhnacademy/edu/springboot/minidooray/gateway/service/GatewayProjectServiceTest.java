package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.request.ProjectRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
class GatewayProjectServiceTest {

    RestTemplate restTemplate = new RestTemplate();
    GatewayProjectService gatewayProjectService;


    @BeforeEach
    void setUp(){
        gatewayProjectService = new GatewayProjectService(restTemplate);
    }

    @Test
    void projectExistsRequest() {
    }

    @Test
    void projectCreateRequest() {
        ProjectRequest request = new ProjectRequest(1,"프로젝트DB설계","활성");
        boolean result = gatewayProjectService.projectCreateRequest(request);
        assertTrue(result);

    }

    @Test
    void projectUpdateRequest() {
    }

    @Test
    void projectDeleteRequest() {
        Long projectId = 2L;

        boolean result = gatewayProjectService.projectDeleteRequest(projectId);

        assertTrue(result);
    }
}