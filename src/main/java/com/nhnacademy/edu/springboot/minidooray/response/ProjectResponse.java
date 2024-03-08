package com.nhnacademy.edu.springboot.minidooray.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    long projectId;
    long adminId;
    String name;
    String status;
}