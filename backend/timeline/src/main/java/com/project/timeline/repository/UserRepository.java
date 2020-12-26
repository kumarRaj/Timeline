package com.project.timeline.repository;

import com.project.timeline.model.Follower;
import com.project.timeline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);

    @Override
    Optional<User> findById(Integer userId);

    List<User> findByFirstNameIgnoreCaseContaining(String name);

    List<User> findByLastNameIgnoreCaseContaining(String name);

    List<User> findByIdIn(List<Integer> userIdList);
}
