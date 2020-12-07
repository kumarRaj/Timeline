package com.project.timeline.repository;

import com.project.timeline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);

    @Override
    Optional<User> findById(Integer userId);
}
