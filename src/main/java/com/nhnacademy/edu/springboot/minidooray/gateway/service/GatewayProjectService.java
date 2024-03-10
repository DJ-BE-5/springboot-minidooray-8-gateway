package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Project;
import com.nhnacademy.edu.springboot.minidooray.gateway.exception.ProjectServiceException;
import com.nhnacademy.edu.springboot.minidooray.request.ProjectRequest;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import com.nhnacademy.edu.springboot.minidooray.response.TaskResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class GatewayProjectService {

    private final RestTemplate restTemplate;
    public GatewayProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<ProjectResponse> getProjects(String userId){

        ResponseEntity<List<ProjectResponse>> exchange = restTemplate.exchange("http://localhost:9999/projects/projectList/"+userId,
                HttpMethod.GET, null,new ParameterizedTypeReference<List<ProjectResponse>>(){}
        );


        return exchange.getBody();
    }
    public ProjectResponse getProject(Long projectId){
        ResponseEntity<ProjectResponse> exchange = restTemplate.exchange(
                "http://localhost:9999/projects/"+projectId,
                HttpMethod.GET,
                null,
                ProjectResponse.class
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
            throw new ProjectServiceException("프로젝트 생성을 실패하였습니다.");
        }
        return true;
    }
    //프로젝트 수정 요청
//    public boolean projectDeleteRequest(Long projectId){
//        ResponseEntity<?> response = restTemplate.exchange(
//                "http://localhost:9999/projects/"+projectId,
//                HttpMethod.DELETE,
//                null,
//                Void.class
//        );
//
//        if(response.getStatusCode()!=HttpStatus.NO_CONTENT){
//            throw new ProjectServiceException("프로젝트 삭제를 실패하였습니다.");
//        }
//        return true;
//    }

    public List<Long> getProjectIdList(String userId) {
        ResponseEntity<ArrayList<Long>> response = restTemplate.exchange(
                "http://localhost:9999/projectList/"+userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Long>>() {}
        );
        if(response.getStatusCode()!=HttpStatus.OK){
            throw new ProjectServiceException("프로젝트 리스트를 가져오는 것을 실패하였습니다.");
        }
        return response.getBody();
    }
    public List<String> getMemberList(long projectId) {
        ResponseEntity<ArrayList<String>> response = restTemplate.exchange(
                "http://localhost:9999/memberList/"+projectId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<String>>() {}
        );
        if(response.getStatusCode()!=HttpStatus.OK){
            throw new ProjectServiceException("멤버 리스트를 가져오는 것을 실패하였습니다.");
        }
        return response.getBody();
    }

    public List<TaskResponse> getTaskListByProjectId(long projectId){
        ResponseEntity<ArrayList<TaskResponse>> response = restTemplate.exchange(
                "http://localhost:9999/tasks/taskList/"+projectId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<TaskResponse>>() {}
        );
        if(response.getStatusCode()!=HttpStatus.OK){
            throw new ProjectServiceException("테스크 리스트를 가져오는 것을 실패하였습니다.");
        }
        return response.getBody();
    }
}
