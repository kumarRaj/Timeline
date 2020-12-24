package com.project.timeline.service;

import com.project.timeline.model.LikeTable;
import com.project.timeline.model.Post;
import com.project.timeline.model.User;
import com.project.timeline.repository.LikeTableRepository;
import com.project.timeline.repository.PostRepository;
import com.project.timeline.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimelineServiceTest {

    @Test
    public void testIfBodyIsPresentValidateMethodReturnTrue() {
        Post post = new Post();
        post.setBody("Test Body");
        PostRepository postRepository = mock(PostRepository.class);
        TimelineService timelineService = new TimelineService(postRepository, null, null, null);
        assertTrue(timelineService.validate(post));
    }

    @Test
    public void testIfBodyIsNotPresentValidateMethodReturnFalse() {
        Post post = new Post();
        post.setBody("");
        PostRepository postRepository = mock(PostRepository.class);
        TimelineService timelineService = new TimelineService(postRepository, null, null, null);
        assertFalse(timelineService.validate(post));
    }

    @Test
    public void testIfPostIsValidSavePost() {
        PostRepository postRepository = mock(PostRepository.class);
        Post post = new Post();
        post.setBody("Test Body");
        TimelineService timelineService = new TimelineService(postRepository, null, null, null);
        timelineService.create(post);
        verify(postRepository, times(1)).save(post);

    }


    @Test
    public void testIfToggleLikeIsAddingLike() {
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

    @Test
    public void testIfToggleLikeIsDeletingLike() {
        User user = new User();
        user.setId(1);
        Post post = new Post();
        post.setId(1);
        LikeTable likeTable = new LikeTable();
        likeTable.setPost(post);
        likeTable.setLikedBy(user);
        likeTable.setId(1);
        LikeTableRepository likeTableRepository = mock(LikeTableRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeTableRepository.findByPostAndLikedBy(post, user)).thenReturn(Collections.singletonList(likeTable));
        TimelineService timelineService = new TimelineService(postRepository, userRepository, likeTableRepository, null);
        timelineService.toggleLike(likeTable);
        verify(likeTableRepository, times(1)).deleteById(1);
    }

    @Test
    public void testIfPostIsPresentCalculateCount() {
        PostRepository postRepository = mock(PostRepository.class);
        LikeTableRepository likeTableRepository = mock(LikeTableRepository.class);
        Post post = new Post();
        post.setId(1);
        TimelineService timelineService = new TimelineService(postRepository, null, likeTableRepository, null);
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        timelineService.countLikesInAPost(1);

        verify(likeTableRepository, times(1)).findByPost(post);
    }

    @Test
    public void testIfPostIsNotPresentDontCalculateCount() {
        PostRepository postRepository = mock(PostRepository.class);
        LikeTableRepository likeTableRepository = mock(LikeTableRepository.class);
        Post post = new Post();
        post.setId(1);
        TimelineService timelineService = new TimelineService(postRepository, null, likeTableRepository, null);
        when(postRepository.findById(1)).thenReturn(Optional.empty());
        timelineService.countLikesInAPost(1);

        verify(likeTableRepository, times(0)).findByPost(post);
    }


}