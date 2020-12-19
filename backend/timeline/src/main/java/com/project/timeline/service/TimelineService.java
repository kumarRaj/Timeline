package com.project.timeline.service;

import com.project.timeline.model.*;
import com.project.timeline.repository.CommentRepository;
import com.project.timeline.repository.LikeTableRepository;
import com.project.timeline.repository.PostRepository;
import com.project.timeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimelineService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private LikeTableRepository likeTableRepository;

    private CommentRepository commentRepository;

    @Autowired
    public TimelineService(PostRepository postRepository, UserRepository userRepository, LikeTableRepository likeTableRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeTableRepository = likeTableRepository;
        this.commentRepository = commentRepository;
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
        Integer userId = like.getLikedBy().getId();
        Integer postId = like.getPost().getId();
        if (userId != null && postId != null) {
            Optional<User> user = userRepository.findById(userId);
            Optional<Post> post = postRepository.findById(postId);
            if (user.isPresent() && post.isPresent()) {
                like.setLikedBy(user.get());
                like.setPost(post.get());
                List<LikeTable> byPostAndLikedBy = likeTableRepository.findByPostAndLikedBy(like.getPost(), like.getLikedBy());
                if (byPostAndLikedBy.size() == 0)
                    likeTableRepository.save(like);
                else {
                    Integer likeId = byPostAndLikedBy.get(0).getId();
                    likeTableRepository.deleteById(likeId);
                }
            }
        }
    }

    public Integer countLikesInAPost(Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent())
            return likeTableRepository.findByPost(post.get()).size();
        return 0;
    }

    public boolean hasUserLikedPost(Integer userId, Integer postid) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postid);
        if (user.isPresent() && post.isPresent())
            return likeTableRepository.findByPostAndLikedBy(post.get(), user.get()).size() > 0 ? true : false;
        return false;
    }

    public void addComment(Comment comment) {
        if (comment.getCommentedBy().getId() != null && comment.getPost().getId() != null) {
            Optional<User> user = userRepository.findById(comment.getCommentedBy().getId());
            Optional<Post> post = postRepository.findById(comment.getPost().getId());
            if (user.isPresent() && post.isPresent()) {
                comment.setCommentedBy(user.get());
                comment.setPost(post.get());
                commentRepository.save(comment);
            }
        }
    }

    public List<CommentWrapper> getCommentsForPost(Integer postId) {
        List<Comment> comments = new ArrayList<>();
        List<CommentWrapper> commentWrappers = new ArrayList<>();
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            comments = commentRepository.findByPost(post.get());
            for(Comment comment: comments){
                commentWrappers.add(new CommentWrapper(comment.getCommentedBy().getId(), comment.getPost().getId(), comment.getCommentedBy().getFirstName(), comment.getComment()));
            }
        }
        return commentWrappers;
    }

    public List<PostWrapper> getTimelineOfUser(Integer userId) {
        List<PostWrapper> posts = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Post> postList = postRepository.findByCreatedBy(user.get());
            for (Post post : postList) {
                int likeCount = likeTableRepository.countLikesInAPost(post.getId());
                PostWrapper postWrapper = new PostWrapper(user.get().getId(), user.get().getFirstName(), post.getId(), likeCount, post.getBody());
                List<LikeTable> tempList = likeTableRepository.findByPostAndLikedBy(post, user.get());
                if(tempList != null && tempList.size() > 0)
                    postWrapper.setIsLikedByUser(true);
                posts.add(postWrapper);
            }
        }
        return posts;
    }
}
