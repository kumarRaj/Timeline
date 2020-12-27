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

    @Query(value = "select DISTINCT p.* from Post p, User u, like_table li, Comment c where u.id in :userIdList and ( p.created_by_id = u.id or li.liked_by_id = u.id or c.commented_by_id = u.id) order by p.created_time desc LIMIT :start, :end", nativeQuery = true)
    List<Post> getHomePageFeed(List<Integer> userIdList, int start, int end);

    @Query(value = "select count(DISTINCT p.id) from Post p, User u, like_table li, Comment c where u.id in :userIdList and ( p.created_by_id = u.id or li.liked_by_id = u.id or c.commented_by_id = u.id) order by p.created_time desc", nativeQuery = true)
    Integer getHomePageFeedCount(List<Integer> userIdList);
}
