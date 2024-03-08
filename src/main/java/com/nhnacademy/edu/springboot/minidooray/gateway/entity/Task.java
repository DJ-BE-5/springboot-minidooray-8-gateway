package com.nhnacademy.edu.springboot.minidooray.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Task {
    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "task_id")
    private List<Tag> tags;

    @OneToOne
    private MileStone mileStone;
}
