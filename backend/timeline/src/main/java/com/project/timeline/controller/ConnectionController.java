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

    @PostMapping(value = "/followers")
    private Follower addFollowersForUser(@RequestBody Follower follower) {
        return connectionService.addFollowers(follower);
    }

    @GetMapping(value = "/followers/{loggedInUserId}")
    private List<User> getFollowersOfUser(@PathVariable Integer loggedInUserId) {
        return connectionService.getFollowers(loggedInUserId);
    }

    @GetMapping(value = "/following/{loggedInUserId}")
    private List<User> getUserFollowing(@PathVariable Integer loggedInUserId) {
        return connectionService.getUserFollowing(loggedInUserId);
    }

    @GetMapping(value = "followers/{loggedInUserId}/follows/{userId}")
    private boolean isLoggedInUserFollowingUser(@PathVariable(name = "loggedInUserId") Integer loggedInUserId,
                                                @PathVariable(name = "userId") Integer userId){
        return connectionService.isLoggedInUserFollowingUser(loggedInUserId, userId);
    }

    @DeleteMapping(value = "/followers")
    private void unFollowUser(@RequestBody Follower follower){
        connectionService.unFollowUser(follower);
    }

}
