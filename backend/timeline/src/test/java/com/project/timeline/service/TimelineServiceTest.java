package com.project.timeline.service;

import com.project.timeline.model.*;
import com.project.timeline.repository.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

class TimelineServiceTest extends TestCase {

    private TimelineService timelineService;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private LikeTableRepository likeTableRepository;
    private CommentRepository commentRepository;
    private FollowerRepository followerRepository;
    private ConnectionService connectionService;

    @BeforeEach
    public void setUp(){
        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);
        commentRepository = mock(CommentRepository.class);
        likeTableRepository = mock(LikeTableRepository.class);
        followerRepository = mock(FollowerRepository.class);
        connectionService = new ConnectionService(followerRepository, userRepository);
        timelineService = new TimelineService(postRepository, userRepository, likeTableRepository, commentRepository, connectionService);
    }

    @AfterEach
    public void tearDown(){
        timelineService = null;
        postRepository = null;
        userRepository = null;
        likeTableRepository = null;
        commentRepository = null;
        followerRepository = null;
    }

    @Test
    public void testIfBodyIsPresentValidateMethodReturnTrue() {
        Post post = new Post();
        post.setBody("Test Body");
        assertTrue(timelineService.validate(post));
    }

    @Test
    public void testIfBodyIsNotPresentValidateMethodReturnFalse() {
        Post post = new Post();
        post.setBody("");
        assertFalse(timelineService.validate(post));
    }

    @Test
    public void testIfPostIsValidSavePost() {
        Post post = new Post();
        post.setBody("Test Body");
        timelineService.create(post);
        verify(postRepository, times(1)).save(post);

    }


    @Test
    public void testIfToggleLikeIsAddingLike() {
        User user = new User();
        user.setId(1);
        Post post = new Post();
        post.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeTableRepository.findByPostAndLikedBy(post, user)).thenReturn(Collections.emptyList());
        LikeTable likeTable = new LikeTable();
        likeTable.setPost(post);
        likeTable.setLikedBy(user);
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
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeTableRepository.findByPostAndLikedBy(post, user)).thenReturn(Collections.singletonList(likeTable));
        timelineService.toggleLike(likeTable);
        verify(likeTableRepository, times(1)).deleteById(1);
    }

    @Test
    public void testIfPostIsPresentCalculateCount() {
        Post post = new Post();
        post.setId(1);
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        timelineService.countLikesInAPost(1);
        verify(likeTableRepository, times(1)).findByPost(post);
    }

    @Test
    public void testIfPostIsNotPresentDontCalculateCount() {
        Post post = new Post();
        post.setId(1);
        when(postRepository.findById(1)).thenReturn(Optional.empty());
        timelineService.countLikesInAPost(1);
        verify(likeTableRepository, times(0)).findByPost(post);
    }

    @Test
    public void testIfUserHasLikedPostReturnTrue() {
        User user = new User();
        Post post = new Post();
        LikeTable likeTable = new LikeTable();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(likeTableRepository.findByPostAndLikedBy(post, user)).thenReturn(Collections.singletonList(likeTable));
        assertTrue(timelineService.hasUserLikedPost(1, 1));
        verify(likeTableRepository, times(1)).findByPostAndLikedBy(post, user);
    }

    @Test
    public void testIfUserHasNotLikedPostReturnFalse() {
        User user = new User();
        Post post = new Post();
        LikeTable likeTable = new LikeTable();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        assertFalse(timelineService.hasUserLikedPost(1, 1));
        verify(likeTableRepository, times(1)).findByPostAndLikedBy(post, user);
    }

    @Test
    public void testIfUserIsNotPresentHasLikedPostReturnFalse() {
        Post post = new Post();
        User user = new User();
        LikeTable likeTable = new LikeTable();
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        assertFalse(timelineService.hasUserLikedPost(1, 1));
        verify(likeTableRepository, times(0)).findByPostAndLikedBy(post, user);
    }

    @Test
    public void testIfPostIsNotPresentHasLikedPostReturnFalse() {
        User user = new User();
        Post post = new Post();
        LikeTable likeTable = new LikeTable();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.empty());
        assertFalse(timelineService.hasUserLikedPost(1, 1));
        verify(likeTableRepository, times(0)).findByPostAndLikedBy(post, user);
    }

    @Test
    public void testAddCommentIfUserAndPostAreValid() {
        User user = new User();
        user.setId(1);
        Post post = new Post();
        post.setId(1);
        Comment comment = new Comment();
        comment.setCommentedBy(user);
        comment.setPost(post);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        timelineService.addComment(comment);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testAddCommentIfUserIsInvalidAndPostAreValid() {
        User user = new User();
        user.setId(1);
        Post post = new Post();
        post.setId(1);
        Comment comment = new Comment();
        comment.setCommentedBy(user);
        comment.setPost(post);
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        timelineService.addComment(comment);
        verify(commentRepository, times(0)).save(comment);
    }

    @Test
    public void testAddCommentIfUserIsValidAndPostIsInvalid() {
        User user = new User();
        user.setId(1);
        Post post = new Post();
        post.setId(1);
        Comment comment = new Comment();
        comment.setCommentedBy(user);
        comment.setPost(post);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.empty());
        timelineService.addComment(comment);
        verify(commentRepository, times(0)).save(comment);
    }

    @Test
    public void testGetCommentsForPostForValidPostId() {
        Post post = new Post();
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        assertEquals(Collections.emptyList(), timelineService.getCommentsForPost(1));
        verify(commentRepository, times(1)).findByPost(post);
    }

    @Test
    public void testGetTimelineOfUserForValidUserAndValidPost() {
        User user = new User();
        user.setId(1);
        user.setFirstName("FirstName");
        Post post = new Post();
        post.setCreatedBy(user);
        post.setId(2);
        post.setBody("Body");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findByCreatedBy(user)).thenReturn(Collections.singletonList(post));
        when(likeTableRepository.countLikesInAPost(2)).thenReturn(0);
        when(likeTableRepository.findByPostAndLikedBy(post, user)).thenReturn(Collections.emptyList());
        Collections.singletonList(new PostWrapper(user.getId(), user.getFirstName(), post.getId(), 0, post.getBody()));
        assertEquals(
                Collections.singletonList(new PostWrapper(user.getId(), user.getFirstName(), post.getId(), 0, post.getBody())),
                timelineService.getTimelineOfUser(user.getId()));

        verify(userRepository).findById(user.getId());
        verify(postRepository).findByCreatedBy(user);
        verify(likeTableRepository).countLikesInAPost(post.getId());
        verify(likeTableRepository).findByPostAndLikedBy(post, user);

    }


}