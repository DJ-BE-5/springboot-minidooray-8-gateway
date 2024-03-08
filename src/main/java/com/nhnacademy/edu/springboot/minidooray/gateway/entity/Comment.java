package com.nhnacademy.edu.springboot.minidooray.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Comment {
    @Id
    private String id;
    @ManyToOne
    private Task task;
}
