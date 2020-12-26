package com.project.timeline.repository;

import com.project.timeline.model.Follower;
import com.project.timeline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowerRepository extends JpaRepository<Follower, Integer> {

    Follower save(Follower follower);

    List<Follower> findByLoggedInUser(User loggedInUser);

    List<Follower> findByLoggedInUserAndFollower(User loggedInUser, User Follower);

    void deleteById(Integer followerId);

    List<Follower> findByFollower(User follower);

}
