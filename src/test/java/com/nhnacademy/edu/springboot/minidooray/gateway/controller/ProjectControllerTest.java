package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;

class ProjectControllerTest {
    MockMvc mockMvc;
    private GatewayProjectService projectService;

    private ProjectController projectController;
    @BeforeEach
    void setUp(){
        projectService = mock(GatewayProjectService.class);
        projectController = new ProjectController(projectService);
    }

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