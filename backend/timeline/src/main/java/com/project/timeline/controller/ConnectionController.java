package com.project.timeline.controller;

import com.project.timeline.model.Follower;
import com.project.timeline.model.User;
import com.project.timeline.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/connect")
public class ConnectionController {

    private ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping(value = "/follower")
    private Follower addFollowersForUser(@RequestBody Follower follower) {
        return connectionService.addFollowers(follower);
    }

    @GetMapping(value = "/follower/{loggedInUserId}")
    private List<User> getFollowersOfUser(@PathVariable Integer loggedInUserId) {
        return connectionService.getFollowers(loggedInUserId);
    }

    @GetMapping(value = "/following/{loggedInUserId}")
    private List<User> getUserFollowing(@PathVariable Integer loggedInUserId) {
        return connectionService.getUserFollowing(loggedInUserId);
    }

    @GetMapping(value = "follower/{loggedInUserId}/follows/{userId}")
    private boolean isLoggedInUserFollowingUser(@PathVariable(name = "loggedInUserId") Integer loggedInUserId,
                                                @PathVariable(name = "userId") Integer userId){
        return connectionService.isLoggedInUserFollowingUser(loggedInUserId, userId);
    }

    @DeleteMapping(value = "/{loggedInUserId}/unfollows/{unFollowUserId}")
    private void unFollowUser(@PathVariable(name = "loggedInUserId") Integer loggedInUserId,
                                                @PathVariable(name = "unFollowUserId") Integer unFollowUserId){
        connectionService.unFollowUser(loggedInUserId, unFollowUserId);
    }

}
