package com.nhnacademy.edu.springboot.minidooray.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    long adminId;
    String name;
    String status;
}