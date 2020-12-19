package com.project.timeline.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentWrapper {

    private Integer userId;

    private Integer postId;

    private String commentedBy;

    private String comment;

    public CommentWrapper(Integer userId, Integer postId, String commentedBy, String comment) {
        this.userId = userId;
        this.postId = postId;
        this.commentedBy = commentedBy;
        this.comment = comment;
    }
}
