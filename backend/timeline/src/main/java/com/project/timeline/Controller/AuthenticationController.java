package com.project.timeline.controller;

import com.project.timeline.model.User;
import com.project.timeline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signUp")
    private User save(@RequestBody User user) {
        return userService.save(user);
    }


}
