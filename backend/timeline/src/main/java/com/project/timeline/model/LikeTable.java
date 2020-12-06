package com.project.timeline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class LikeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User likedBy;

    @OneToOne
    private Post post;

    public LikeTable(User likedBy, Post post) {
        this.likedBy = likedBy;
        this.post = post;
    }
}
