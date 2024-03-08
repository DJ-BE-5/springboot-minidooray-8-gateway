package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.LoginRequestDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
    private final GatewayProjectService projectService;

    @Autowired
    public ProjectController(GatewayProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project/{projectId}")
    public String signup(@PathVariable String projectId,
                         @RequestHeader String userId) {
        return "project/" + projectId;
    }
    @GetMapping("/projects")
    public String getProjects(Model model){
        model.addAttribute(
                "projects",
                projectService.getProjects()
        );
        return "viewProjects";
    }

    @PostMapping("/project/{projectId}")
    public String signupPost(@PathVariable String projectId,
                             @RequestHeader String userId,
                             @RequestBody LoginRequestDTO loginRequest) {
        projectService.projectExistsRequest(null);
        return "project/" + projectId;
    }
}
