package com.nhnacademy.edu.springboot.minidooray.gateway.interceptor;

import com.nhnacademy.edu.springboot.minidooray.gateway.exception.AuthNotPermittedException;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
    private final GatewayAccountService accountService;

    @Autowired
    public LoginCheckInterceptor(GatewayAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 로그인 체크 인터셉터
        // POST: 로그인 요청(아이디+비밀번호) 인 경우
        if (request.getMethod().equals(HttpMethod.POST.toString())) {
            // 우선 세션이 생성되었는지, 생성된 세션에 id 값이 저장되었는지 확인
            if (request.getSession(false) != null
                    && request.getSession(false).getAttribute("id") != null) {
                //세션이 생성되어 있고 id 값도 저장되어 있다면 redis 내부에 값이 있는지 확인
                if (valueOperations.get("minidooray8_id") != null
                        && accountService.userExistsRequest(valueOperations.get("minidooray8_id"))) {
                    // 전부 통과했다면 인증 종료, 다음 인터셉터로
                    log.debug("{}", valueOperations.get("minidooray8_id"));
                    return HandlerInterceptor.super.preHandle(request, response, handler);

                    // redis 통과하지 못한경우
                } else throw new AuthNotPermittedException();
                // session 통과하지 못한 경우
            } else throw new AuthNotPermittedException();

            // else(GET): 로그인 페이지 요청인 경우
        } else {
            // 이 경우 이미 로그인 된 경우 메인 페이지로 리다이렉트시켜야 함
            // 세션 검사
            if (request.getSession(false) != null
                    && request.getSession(false).getAttribute("id") != null) {
                //redis 검사
                if (valueOperations.get("minidooray8_id") != null
                        && accountService.userExistsRequest(valueOperations.get("minidooray8_id"))) {
                    // 인증 통과하여 이미 로그인됨이 확인된 경우 리다이렉트
                    response.sendRedirect("index");
                    // 이후 코드가 실행되지 못하도록 false 리턴
                    log.debug("{}", valueOperations.get("minidooray8_id"));
                    return false;

                    // redis 통과 못한경우
                } else return HandlerInterceptor.super.preHandle(request, response, handler);
                // session 통과 못한경우
            } else return HandlerInterceptor.super.preHandle(request, response, handler);


        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
