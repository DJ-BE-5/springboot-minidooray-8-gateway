package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.domain.LoginRequestDTO;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping("/project/{projectId}")
    public String signupPost(@PathVariable String projectId,
                             @RequestHeader String userId,
                             @RequestBody LoginRequestDTO loginRequest) {
        projectService.projectExistsRequest(null);
        return "project/" + projectId;
    }
}
