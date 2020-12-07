package com.project.timeline.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User createdBy;

    private Date createdTime;

    private Date modifiedTime;

    private String body;

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date(System.currentTimeMillis());
    }

}
