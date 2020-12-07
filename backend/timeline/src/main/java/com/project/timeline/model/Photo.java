package com.project.timeline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String path;

    @OneToOne
    private Post post;

    public Photo(String path) {
        this.path = path;
    }
}
