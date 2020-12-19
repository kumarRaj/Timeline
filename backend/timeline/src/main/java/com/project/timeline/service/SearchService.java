package com.project.timeline.service;

import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import com.project.timeline.repository.PostRepository;
import com.project.timeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {


    private PostRepository postRepository;

    private UserRepository userRepository;

    @Autowired
    public SearchService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> searchBlog(String query) {
        List<Post> posts = new ArrayList<>();
        posts.addAll(postRepository.findByBodyIgnoreCaseContaining(query));
        return posts;
    }

    public List<User> searchUser(String query) {
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findByFirstNameIgnoreCaseContaining(query));
        users.addAll(userRepository.findByLastNameIgnoreCaseContaining(query));
        return users;
    }
}
