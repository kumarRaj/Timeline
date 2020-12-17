package com.project.timeline.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWrapper {

    private Integer userId;

    private Integer postId;

    private Integer likesCount;

    private String body;

    public PostWrapper(Integer userId, Integer postId, Integer likesCount, String body) {
        this.userId = userId;
        this.postId = postId;
        this.likesCount = likesCount;
        this.body = body;
    }
}
