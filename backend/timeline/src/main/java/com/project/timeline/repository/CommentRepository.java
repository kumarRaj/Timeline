package com.project.timeline.repository;

import com.project.timeline.model.Comment;
import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment save(Comment comment);

    List<Comment> findByPost(Post post);

    List<Comment> findByCommentedBy(User commentedBy);


}
