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
public class Project {
    @Id
    @Column(name = "project_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;// = admin_id

    private String name;
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MileStone> mileStone;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tag;
}
