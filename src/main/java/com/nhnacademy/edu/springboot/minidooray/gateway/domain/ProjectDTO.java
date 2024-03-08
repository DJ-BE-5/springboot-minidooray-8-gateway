package com.nhnacademy.edu.springboot.minidooray.gateway.domain;

import com.nhnacademy.edu.springboot.minidooray.gateway.entity.Task;

import java.util.List;

public interface ProjectDTO {
    String getId();

    List<Task> getTasks();
}
