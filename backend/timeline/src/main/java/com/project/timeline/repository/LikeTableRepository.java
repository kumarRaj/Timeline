package com.project.timeline.repository;

import com.project.timeline.model.LikeTable;
import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeTableRepository extends JpaRepository<LikeTable, Integer> {

    LikeTable save(LikeTable likeTable);

    List<LikeTable> findByPost(Post post);

    List<LikeTable> findByLikedBy(User user);

    List<LikeTable> findByPostAndLikedBy(Post post, User user);

    void deleteById(Integer likeId);
}
