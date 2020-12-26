package com.project.timeline.controller;

import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import com.project.timeline.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/blog")
    private List<Post> searchBlog(@RequestParam(name = "query") String query){
        return searchService.searchBlog(query);
    }

    @GetMapping(value = "/person")
    private List<User> searchUser(@RequestParam(name = "query") String query){
        return searchService.searchUser(query);
    }


}
