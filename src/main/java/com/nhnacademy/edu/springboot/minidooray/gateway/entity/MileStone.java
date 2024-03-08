package com.nhnacademy.edu.springboot.minidooray.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class MileStone {
    @Id
    private Integer id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    private Project project;

    @OneToOne
    private Task task;
}
