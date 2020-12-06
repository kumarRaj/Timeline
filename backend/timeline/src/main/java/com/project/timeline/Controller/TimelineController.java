package com.project.timeline.Controller;

import com.project.timeline.model.Post;
import com.project.timeline.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @PostMapping(value = "/create")
    public Post create(@RequestBody Post post){
        return timelineService.create(post);
    }

    @PostMapping(value = "post/{postId}/like/{userId}")
    private void addLike(@PathVariable Integer postId, @PathVariable Integer userId){
        timelineService.addLike(postId, userId);
    }

}
