package com.project.timeline.service;

import com.project.timeline.model.User;

import static org.mockito.Mockito.*;

import com.project.timeline.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void shouldReturnFalseForInvalidEmail() {
        // Setup
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        User input = new User("abcd", "abcd", "user#domain.com", "abcdefgh", "", null);

        // Action
        User user = userService.save(input);

        // Assertion
        assertNull(user);
        verify(userRepository, times(0)).save(input);
    }

    @Test
    void shouldReturnTrueForValidEmail() {
        // Setup
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        User input = new User("abcd", "abcd", "user@domain.com", "abcdefgh", "", null);

        // Action
        User user = userService.save(input);

        // Assertion
        assertNull(user);
        verify(userRepository, times(1)).save(input);
    }

}