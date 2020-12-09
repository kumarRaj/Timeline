package com.project.timeline.service;

import com.project.timeline.model.Comment;
import com.project.timeline.model.LikeTable;
import com.project.timeline.model.Post;
import com.project.timeline.model.User;
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

    public void addComment(Comment comment) {
        if(comment.getCommentedBy().getId() != null && comment.getPost().getId() != null) {
            Optional<User> user = userRepository.findById(comment.getCommentedBy().getId());
            Optional<Post> post = postRepository.findById(comment.getPost().getId());
            if(user.isPresent() && post.isPresent()) {
                comment.setCommentedBy(user.get());
                comment.setPost(post.get());
                commentRepository.save(comment);
            }
        }
    }

    public List<Comment> getCommentsForPost(Integer postId) {
        List<Comment> comments = new ArrayList<>();

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent())
            comments.addAll(commentRepository.findByPost(post.get()));

        return comments;
    }
}
