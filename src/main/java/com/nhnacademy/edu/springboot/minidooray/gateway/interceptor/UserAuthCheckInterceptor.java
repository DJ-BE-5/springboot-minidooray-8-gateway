package com.nhnacademy.edu.springboot.minidooray.gateway.interceptor;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.User;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import com.nhnacademy.edu.springboot.minidooray.response.ProjectResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eclipse.jetty.client.api.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class UserAuthCheckInterceptor implements HandlerInterceptor {

    public UserAuthCheckInterceptor(GatewayProjectService gatewayProjectService) {
        this.gatewayProjectService = gatewayProjectService;
    }

    private final GatewayProjectService gatewayProjectService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String projectId = request.getParameterMap().get("projectId")[0];

        if(userId == null){
            // 로그인 안되어있으면 로그인하라고 보내버리기
            response.sendRedirect("loginform");
            return false;
        }
        ProjectResponse pr = gatewayProjectService.getProject(Integer.valueOf(projectId));

        // 프로젝트의 관리자와 로그인된 유저의 아이디가 다르면 false
        if(!userId.equals(pr.getAdminId())){
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
