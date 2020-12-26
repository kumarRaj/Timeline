package com.project.timeline.service;

import com.project.timeline.model.Follower;
import com.project.timeline.model.User;
import com.project.timeline.repository.FollowerRepository;
import com.project.timeline.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ConnectionServiceTest extends TestCase {

    private UserRepository userRepository;
    private FollowerRepository followerRepository;
    private ConnectionService connectionService;
    private User loggedinUser;
    private User followerUser;

    @BeforeEach
    public void setUp(){
        userRepository = mock(UserRepository.class);
        followerRepository = mock(FollowerRepository.class);
        connectionService = new ConnectionService(followerRepository, userRepository);
        loggedinUser = new User();
        loggedinUser.setId(1);
        followerUser = new User();
        followerUser.setId(4);
        assertNotNull(userRepository);
        assertNotNull(followerRepository);
        assertNotNull(connectionService);
    }

    @AfterEach
    public void tearDown(){
        userRepository = null;
        followerRepository = null;
        connectionService = null;
        loggedinUser = null;
        followerUser = null;
        assertNull(userRepository);
        assertNull(followerRepository);
        assertNull(connectionService);
    }

    @Test
    public void testAddFollower(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByLoggedInUserAndFollower(loggedinUser, followerUser)).thenReturn(Collections.emptyList());
        connectionService.addFollowers(follower);
        verify(followerRepository, times(1)).save(follower);
        verify(userRepository, times(2)).findById(1);
        verify(userRepository, times(2)).findById(4);
    }

    @Test
    public void testUserAldreadyAFollowerAddFollowerReturnsNull(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByLoggedInUserAndFollower(loggedinUser, followerUser)).thenReturn(Collections.singletonList(follower));
        connectionService.addFollowers(follower);
        verify(followerRepository, times(0)).save(follower);
        verify(userRepository, times(2)).findById(1);
        verify(userRepository, times(2)).findById(4);
    }

    @Test
    public void testGetFollowersShouldReturn1Follower(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByFollower(followerUser)).thenReturn(Collections.singletonList(follower));
        when(userRepository.findByIdIn(Collections.singletonList(1))).thenReturn(Collections.singletonList(loggedinUser));
        List<User> followers = connectionService.getFollowers(4);
        assertEquals(1, followers.size());
        assertEquals(new Integer(1), followers.get(0).getId());
        verify(userRepository).findById(4);
        verify(userRepository).findByIdIn(Collections.singletonList(1));
        verify(followerRepository).findByFollower(followerUser);
    }

    @Test
    public void testIsLoggedInUserFollowingUserReturnTrue(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByLoggedInUserAndFollower(loggedinUser, followerUser)).thenReturn(Collections.singletonList(follower));
        assertEquals(true, connectionService.isLoggedInUserFollowingUser(1, 4));
        verify(followerRepository).findByLoggedInUserAndFollower(loggedinUser,followerUser);
        verify(userRepository).findById(1);
        verify(userRepository).findById(4);
    }

    @Test
    public void testIsLoggedInUserFollowingUserReturnFalse(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByLoggedInUserAndFollower(loggedinUser, followerUser)).thenReturn(Collections.emptyList());
        assertEquals(false, connectionService.isLoggedInUserFollowingUser(1, 4));
        verify(followerRepository).findByLoggedInUserAndFollower(loggedinUser,followerUser);
        verify(userRepository).findById(1);
        verify(userRepository).findById(4);
    }

    @Test
    public void testUnFollowUserResultDeletesUser(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        follower.setId(5);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByLoggedInUserAndFollower(loggedinUser, followerUser)).thenReturn(Collections.singletonList(follower));
        connectionService.unFollowUser(1, 4);
        verify(followerRepository).deleteById(5);
    }

    @Test
    public void testUnFollowUserResultDoesNotDeletesUser(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        follower.setId(5);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(userRepository.findById(4)).thenReturn(Optional.of(followerUser));
        when(followerRepository.findByLoggedInUserAndFollower(loggedinUser, followerUser)).thenReturn(Collections.emptyList());
        connectionService.unFollowUser(1, 4);
        verify(followerRepository,times(0)).deleteById(5);
    }

    @Test
    public void testGetUserFollowingReturns1Result(){
        Follower follower = new Follower();
        follower.setLoggedInUser(loggedinUser);
        follower.setFollower(followerUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(loggedinUser));
        when(followerRepository.findByLoggedInUser(loggedinUser)).thenReturn(Collections.singletonList(follower));
        when(userRepository.findByIdIn(Collections.singletonList(4))).thenReturn(Collections.singletonList(followerUser));
        List<User> followers = connectionService.getUserFollowing(1);
        assertEquals(1, followers.size());
        assertEquals(new Integer(4), followers.get(0).getId());
        verify(userRepository).findById(1);
        verify(userRepository).findByIdIn(Collections.singletonList(4));
        verify(followerRepository).findByLoggedInUser(loggedinUser);
    }



}