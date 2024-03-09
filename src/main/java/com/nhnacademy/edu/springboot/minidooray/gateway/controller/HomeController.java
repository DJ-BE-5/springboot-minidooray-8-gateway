package com.nhnacademy.edu.springboot.minidooray.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/hello/world")
    public String gotoProject(){
        return "project";
    }

    @GetMapping("/test/world")
    public String gotohello(){
        return "project";
    }
}
