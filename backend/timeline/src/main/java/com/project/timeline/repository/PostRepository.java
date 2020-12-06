package com.project.timeline.repository;

import com.project.timeline.model.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post save(Post post);

}
