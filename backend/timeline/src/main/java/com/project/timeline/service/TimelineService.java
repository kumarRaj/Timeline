package com.project.timeline.service;

import com.project.timeline.model.LikeTable;
import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import com.project.timeline.repository.LikeTableRepository;
import com.project.timeline.repository.PostRepository;
import com.project.timeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private LikeTableRepository likeTableRepository;

    @Autowired
    public TimelineService(PostRepository postRepository, UserRepository userRepository, LikeTableRepository likeTableRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeTableRepository = likeTableRepository;
    }

    public Post create(Post post) {
        if (validate(post))
            return postRepository.save(post);
        return post;
    }

    public boolean validate(Post post) {
        if (post.getBody().equals(""))
            return false;
        return true;
    }

    public void toggleLike(LikeTable like) {
        like.setLikedBy(userRepository.findById(like.getLikedBy().getId()).get());
        like.setPost(postRepository.findById(like.getPost().getId()).get());
        List<LikeTable> byPostAndLikedBy = likeTableRepository.findByPostAndLikedBy(like.getPost(), like.getLikedBy());
        if(byPostAndLikedBy.size() == 0)
            likeTableRepository.save(like);
        else{
            Integer likeId = byPostAndLikedBy.get(0).getId();
            likeTableRepository.deleteById(likeId);
        }
    }

    public Integer countLikesInAPost(Integer postId) {
        Post post = postRepository.findById(postId).get();
        return likeTableRepository.findByPost(post).size();
    }

    public boolean hasUserLikedPost(Integer userId, Integer postid){
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postid).get();
        return likeTableRepository.findByPostAndLikedBy(post,user).size() > 0 ? true : false;
    }
}
