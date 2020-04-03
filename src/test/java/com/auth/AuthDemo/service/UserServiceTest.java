package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.user.UserValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    private final long expectedId = 12L;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidationService userValidationService;
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        initMocks(this);
        user = new User();
        user.setId(expectedId);
    }

    @Test
    void create() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(expectedId, userService.create(user));
    }

    @Test
    void update() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(expectedId, userService.create(user));
    }

    @Test
    void findById() {
        final Optional<User> result = Optional.of(user);
        when(userRepository.findById(expectedId)).thenReturn(result);
        assertEquals(user, userService.findById(expectedId));
    }

    @Test
    void deleteById() {
        userService.deleteById(expectedId);
        verify(userRepository).deleteById(expectedId);
    }

    @Test
    void findAll() {
        final List<User> expected = new ArrayList<User>() {{
            add(user);
        }};
        when(userRepository.findAll()).thenReturn(expected);

        final List<User> actual = userService.findAll();

        verify(userRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByName() {
        final String userName = "Java";
        when(userRepository.findByName(userName)).thenReturn(Optional.of(user));

        final User actual = userService.findByName(userName);

        verify(userRepository).findByName(userName);
        assertEquals(user, actual);
    }
}