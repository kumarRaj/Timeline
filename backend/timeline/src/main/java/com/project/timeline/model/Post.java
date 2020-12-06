package com.project.timeline.model;

import javax.persistence.*;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User createdBy;

    private Date createdTime;

    private Date modifiedTime;

    private String body;

    public Post(User createdBy, Date createdTime, Date modifiedTime, String body) {
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.body = body;
    }

}
