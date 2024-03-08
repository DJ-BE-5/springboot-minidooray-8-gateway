package com.nhnacademy.edu.springboot.minidooray.gateway.service;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class GatewayProjectService {

    //프로젝트 검색 요청
    public boolean projectExistsRequest(String id) {
        return false;
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
