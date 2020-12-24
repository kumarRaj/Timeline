package com.project.timeline.service;

import com.project.timeline.model.LikeTable;
import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import com.project.timeline.repository.LikeTableRepository;
import com.project.timeline.repository.PostRepository;
import com.project.timeline.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimelineServiceTest {

    @Test
    public void testIfToggleLikeIsAddingLike(){
        User user = new User();
        user.setId(1);
        Post post = new Post();
        post.setId(1);
        LikeTableRepository likeTableRepository = mock(LikeTableRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeTableRepository.findByPostAndLikedBy(post, user)).thenReturn(Collections.emptyList());
        LikeTable likeTable = new LikeTable();
        likeTable.setPost(post);
        likeTable.setLikedBy(user);
        TimelineService timelineService = new TimelineService(postRepository, userRepository, likeTableRepository, null);

        timelineService.toggleLike(likeTable);

        verify(likeTableRepository, times(1)).save(likeTable);
    }

}