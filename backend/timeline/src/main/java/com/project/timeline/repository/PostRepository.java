package com.project.timeline.repository;

import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post save(Post post);

    @Override
    Optional<Post> findById(Integer postId);

    List<Post> findByCreatedBy(User user);

    @Query("SELECT p FROM Post p WHERE p.body like '%:body%'")
    List<Post> searchByBody(String body);

    List<Post> findByBodyIgnoreCaseContaining(String body);
}
