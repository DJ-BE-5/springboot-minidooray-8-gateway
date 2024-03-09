package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Project;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final GatewayProjectService projectService;
    private final String userId = "jieun";     //TODO : 현재 접속중인 아이디 받아와서 사용하기

    @Autowired
    public ProjectController(GatewayProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/project")
    public String signup(@PathVariable String projectId,
                         @RequestHeader String userId) {
        return "project/" + projectId;
    }

    @GetMapping("/project/{projectId}")
    public String getProject(@PathVariable Integer projectId,
                             Model model) {
        ProjectResponse pr = projectService.getProject(projectId);
        Project project = new Project();
        project.setId((int) pr.getProjectId());
        project.setName(pr.getName());
        // todo: ProjectResponse에서 adminId를 Project User에 넣기
        //project.setUser();
        project.setStatus(pr.getStatus());
        model.addAttribute("project", project);
        return "project";
    }

    @GetMapping()
    public String getProjectList(Model model) {
        model.addAttribute(
                "projects",
                projectService.getProjects(userId)
        );
        return "viewProjects";
    }

//    @GetMapping("{projectId}")
//    public String getProjects(Model model){
//        model.addAtrribute(
//
//        );
//    }

//    @PostMapping("/project/{projectId}")
//    public String signupPost(@PathVariable String projectId,
//                             @RequestHeader String userId,
//                             @RequestBody LoginRequestDTO loginRequest) {
//        projectService.projectExistsRequest(null);
//        return "project/" + projectId;
//    }

}
