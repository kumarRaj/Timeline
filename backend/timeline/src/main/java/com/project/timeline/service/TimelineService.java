package com.project.timeline.service;

import com.project.timeline.model.Post;
import com.project.timeline.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TimelineService {

    @Autowired
    private PostRepository postRepository;

    public Post create(Post post) {
        if(validate(post))
            return postRepository.save(post);
        return post;
    }

    public boolean validate(Post post){
        if(post.getBody().equals(""))
            return false;
        return true;
    }

    public void addLike(Integer postId, Integer userId) {

    }
}
