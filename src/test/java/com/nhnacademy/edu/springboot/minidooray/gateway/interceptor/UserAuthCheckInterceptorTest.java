package com.nhnacademy.edu.springboot.minidooray.gateway.interceptor;

import com.nhnacademy.edu.springboot.minidooray.gateway.advice.GlobalExceptionHandler;
import com.nhnacademy.edu.springboot.minidooray.gateway.controller.HomeController;
import com.nhnacademy.edu.springboot.minidooray.gateway.interceptor.UserAuthCheckInterceptor;
import com.nhnacademy.edu.springboot.minidooray.gateway.service.GatewayProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class UserAuthCheckInterceptorTest {
    MockMvc mockMvc;
    HttpHeaders httpHeaders;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new HomeController())
                .setControllerAdvice(GlobalExceptionHandler.class)
                .addInterceptors( new UserAuthCheckInterceptor(
                        new GatewayProjectService(
                                new RestTemplate()
                        )
                ))
                .build();

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUAIRedirect() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("userId", "99");

        mockMvc.perform(MockMvcRequestBuilders.
                get("http://localhost:8080/test/world").
                        param("projectId", "1").
                        session(mockHttpSession))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()
        );
    }
    @Test
    void testUAISucess() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("userId", "jieun");

        mockMvc.perform(MockMvcRequestBuilders.
                        get("http://localhost:8080/test/world").
                        param("projectId", "1").
                        session(mockHttpSession))
                .andExpect(MockMvcResultMatchers.status().isOk()
                );
    }
}
