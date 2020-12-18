package com.project.timeline.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWrapper {

    private Integer userId;

    private String username;

    private Integer postId;

    private Integer likesCount;

    private Boolean isLikedByUser = false;

    private String body;

    public PostWrapper(Integer userId, String username, Integer postId, Integer likesCount, String body) {
        this.userId = userId;
        this.username = username;
        this.postId = postId;
        this.likesCount = likesCount;
        this.body = body;
    }
}
