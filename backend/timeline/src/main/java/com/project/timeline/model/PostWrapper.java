package com.project.timeline.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostWrapper that = (PostWrapper) o;
        return userId.equals(that.userId) &&
                username.equals(that.username) &&
                postId.equals(that.postId) &&
                likesCount.equals(that.likesCount) &&
                isLikedByUser.equals(that.isLikedByUser) &&
                body.equals(that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, postId, likesCount, isLikedByUser, body);
    }
}
