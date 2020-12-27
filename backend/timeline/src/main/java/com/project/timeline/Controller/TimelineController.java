package com.project.timeline.controller;

import com.project.timeline.model.*;
import com.project.timeline.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
public class TimelineController {

    private TimelineService timelineService;

    @Autowired
    public TimelineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @PostMapping(value = "/create")
    public Post create(@RequestBody Post post) {
        return timelineService.create(post);
    }

    @PostMapping(value = "/like")
    private void like(@RequestBody LikeTable like) {
        timelineService.toggleLike(like);
    }

    @GetMapping(value = "{postId}/like")
    private Integer countLikesInAPost(@PathVariable Integer postId) {
        return timelineService.countLikesInAPost(postId);
    }

    @PostMapping(value = "/comment")
    private void comment(@RequestBody Comment comment) {
        timelineService.addComment(comment);
    }

    @GetMapping(value = "{postId}/comment")
    private List<CommentWrapper> getCommentsForPost(@PathVariable Integer postId) {
        return timelineService.getCommentsForPost(postId);
    }

    @GetMapping(value = "/timeline/{userId}")
    private List<PostWrapper> getTimelinePosts(@PathVariable Integer userId){
        return timelineService.getTimelineOfUser(userId);
    }

    @GetMapping(value = "/timeline/{userId}/myfeed")
    private RestPostsTemplate getHomePageFeed(@PathVariable Integer userId, @RequestParam Optional<Integer> pageId){
        int pageNum = pageId.orElse(1);
        return timelineService.getHomePageFeed(userId, pageNum);
    }

}
