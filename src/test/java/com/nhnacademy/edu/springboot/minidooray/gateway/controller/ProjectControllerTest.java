package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectControllerTest {

//    @Mock
//    private GatewayProjectService projectService;
//
//    @InjectMocks
//    private ProjectController projectController;
//
//    @Test
//    void getProjectList() {
//        List<ProjectResponse> mockProjects = new ArrayList<>();
//        mockProjects.add(new ProjectResponse(1L,"jieun","name1","status1"));
//        mockProjects.add(new ProjectResponse(2L,"jieun","name2","status2"));
//
//        when(projectService.getProjects(anyString())).thenReturn(mockProjects);
//
//        Model model = mock(Model.class);
//
//        String viewName = projectController.getProjectList(model);
//
//        assert(viewName).equals("viewProjects");
//    }
}