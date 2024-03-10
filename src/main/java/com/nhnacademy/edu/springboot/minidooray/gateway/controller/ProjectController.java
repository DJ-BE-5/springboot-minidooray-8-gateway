package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final GatewayProjectService projectService;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
    private String userId = "jieun";

    @Autowired
    public ProjectController(GatewayProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public String getProjectList(Model model){
        model.addAttribute(
                "adminProjects",
                projectService.getProjects(userId)
        );
        List<Long> projectIdList = projectService.getProjectIdList(userId);
        List<ProjectResponse> memberProjects = new ArrayList<>();
        for(int i=0;i<projectIdList.size();i++){
            memberProjects.add(projectService.getProject(projectIdList.get(i).longValue()));
        }
        model.addAttribute("memberProjects",memberProjects);
        model.addAttribute("user",userId);
        return "viewProjectList";
    }


    @GetMapping("detail/{projectId}")
    public String getProject(@PathVariable("projectId") long projectId,
                             @RequestParam(name = "status", required = false) String status,
                             Model model){
        if(status!=null&&status.equals("admin")){
            model.addAttribute("status","admin");
        }
        model.addAttribute(
                "projectDetail",
                projectService.getProject(projectId)
        );
        String memberList =  projectService.getMemberList(projectId).toString();
        model.addAttribute(
                "memberList",
                memberList.substring(1,memberList.length()-1)

        );
        model.addAttribute(
                "taskList",
                projectService.getTaskListByProjectId(projectId)
        );
        return "viewProject";
    }

//    @PostMapping("/project/{projectId}")
//    public String signupPost(@PathVariable String projectId,
//                             @RequestHeader String userId,
//                             @RequestBody LoginRequestDTO loginRequest) {
//        projectService.projectExistsRequest(null);
//        return "project/" + projectId;
//    }

}
