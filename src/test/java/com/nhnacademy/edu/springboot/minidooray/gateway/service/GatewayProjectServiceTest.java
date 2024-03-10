package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.advice.GlobalExceptionHandler;
import com.nhnacademy.edu.springboot.minidooray.gateway.controller.ProjectController;
import com.nhnacademy.edu.springboot.minidooray.gateway.domain.UserRegisterResponseDTO;
import com.nhnacademy.edu.springboot.minidooray.request.ProjectRequest;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GatewayProjectServiceTest {

    MockMvc mockMvc;
    private GatewayProjectService projectService;

    @Mock
    RestTemplate restTemplate = mock(RestTemplate.class);


    HttpHeaders httpHeaders;

    @BeforeEach
    void setUp() {
        projectService = new GatewayProjectService(restTemplate);

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

    }

    @Test
    void getProjects(){
        List<ProjectResponse> result = new ArrayList<>();
        result.add(new ProjectResponse(1L,"adminId","name","status"));
        result.add(new ProjectResponse(2L,"adminId","name","status"));
        ResponseEntity<List<ProjectResponse>> responseEntity =
                new ResponseEntity<>(result, HttpStatus.OK);

        when(restTemplate.exchange("http://localhost:9999/projects/projectList/adminId",
                HttpMethod.GET, null,new ParameterizedTypeReference<List<ProjectResponse>>(){}
        )).thenReturn(responseEntity);

        Assertions.assertEquals(projectService.getProjects("adminId"),responseEntity.getBody());
    }
    @Test
    void projectCreateRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ProjectRequest request = new ProjectRequest("jieun", "프로젝트DB설계", "활성");
        ProjectResponse response = new ProjectResponse(1L,"jieun", "프로젝트DB설계", "활성");

        HttpEntity<ProjectRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<ProjectResponse> responseEntity =
                new ResponseEntity<>(response, HttpStatus.CREATED);

        when(restTemplate.exchange(
                        "http://localhost:9999/projects",
                        HttpMethod.POST,
                        requestEntity,
                        ProjectResponse.class)).thenReturn(responseEntity);
        boolean result = projectService.projectCreateRequest(request);
        assertTrue(result);

    }


    @Test
    void getProjectIdList() {
//        List<Long> response = new ArrayList<>();
//        response.add(1L);
//        response.add(2L);
//
//        ResponseEntity<List<Long>> responseEntity =
//                new ResponseEntity<>(response, HttpStatus.OK);
//
//        when(restTemplate.exchange(
//                "http://localhost:9999/projectList/adminId",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Long>>() {}
//        )).thenReturn(responseEntity);
//        List<Long> result = projectService.getProjectIdList("adminId");
//        Assertions.assertEquals(response, result);
    }

    @Test
    void getMemberList() {
    }

    @Test
    void getTaskListByProjectId() {
    }
}