package com.project.timeline.service;

import com.project.timeline.model.Follower;
import com.project.timeline.model.User;
import com.project.timeline.repository.FollowerRepository;
import com.project.timeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionService {

    private FollowerRepository followerRepository;
    private UserRepository userRepository;

    @Autowired
    public ConnectionService(FollowerRepository followerRepository, UserRepository userRepository) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    public Follower addFollowers(Follower follower) {
        if (follower.getLoggedInUser().getId() != null && follower.getFollower().getId() != null) {
            Optional<User> loggedInUser = userRepository.findById(follower.getLoggedInUser().getId());
            Optional<User> connectToUser = userRepository.findById(follower.getFollower().getId());
            if (loggedInUser.isPresent() && connectToUser.isPresent() &&
                    !isLoggedInUserFollowingUser(follower.getLoggedInUser().getId(), follower.getFollower().getId())) {
                follower.setLoggedInUser(loggedInUser.get());
                follower.setFollower(connectToUser.get());
                return followerRepository.save(follower);
            }
        }
        return null;
    }

    public List<User> getFollowers(Integer loggedInUserId) {
        List<User> followers = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        Optional<User> loggedInUser = userRepository.findById(loggedInUserId);
        if (loggedInUser.isPresent()) {
            List<Follower> lists = followerRepository.findByFollower(loggedInUser.get());
            for (int i = 0; i < lists.size(); i++) {
                userIdList.add(lists.get(i).getLoggedInUser().getId());
            }
        }
        followers.addAll(userRepository.findByIdIn(userIdList));
        return followers;
    }

    public boolean isLoggedInUserFollowingUser(Integer loggedInUserId, Integer userId) {
        boolean isConnected = false;
        Optional<User> loggedInUser = userRepository.findById(loggedInUserId);
        Optional<User> user = userRepository.findById(userId);
        if (loggedInUser.isPresent() && user.isPresent()) {
            isConnected = followerRepository.findByLoggedInUserAndFollower(loggedInUser.get(), user.get()).size() == 1 ? true : false;
        }
        return isConnected;
    }

    public void unFollowUser(Follower follower) {
        int loggedInUserId = follower.getLoggedInUser().getId();
        int unFollowUserId = follower.getFollower().getId();
        if(isLoggedInUserFollowingUser(loggedInUserId, unFollowUserId)){
            Optional<User> loggedInUser = userRepository.findById(loggedInUserId);
            Optional<User> unFollowUser = userRepository.findById(unFollowUserId);
            if(loggedInUser.isPresent() && unFollowUser.isPresent()){
                Follower tempFollower = followerRepository.findByLoggedInUserAndFollower(loggedInUser.get(), unFollowUser.get()).get(0);
                followerRepository.deleteById(tempFollower.getId());
            }
        }
    }

    public List<User> getUserFollowing(Integer loggedInUserId) {
        List<User> followers = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        Optional<User> loggedInUser = userRepository.findById(loggedInUserId);
        if (loggedInUser.isPresent()) {
            List<Follower> lists = followerRepository.findByLoggedInUser(loggedInUser.get());
            for (int i = 0; i < lists.size(); i++) {
                userIdList.add(lists.get(i).getFollower().getId());
            }
        }
        followers.addAll(userRepository.findByIdIn(userIdList));
        return followers;
    }
}
