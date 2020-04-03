package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.user.UserValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    private final long expectedId = 12L;
    @Mock
    private UserRepository userRepository;
    private User user;
    private UserService studentService;
    private UserValidationService userValidationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userValidationService = Mockito.mock(UserValidationService.class);
        user = new User();
        user.setId(expectedId);
        studentService = new UserService(userRepository, userValidationService);
    }

    @Test
    void create() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(expectedId, studentService.create(user));
    }

    @Test
    void update() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(expectedId, studentService.create(user));
    }

    @Test
    void findById() {
        final Optional<User> result = Optional.of(user);
        when(userRepository.findById(expectedId)).thenReturn(result);
        assertEquals(user, studentService.findById(expectedId));
    }

    @Test
    void deleteById() {
        studentService.deleteById(expectedId);
        verify(userRepository).deleteById(expectedId);
    }

    @Test
    void findAll() {
        final List<User> expected = new ArrayList<User>() {{
            add(user);
        }};
        when(userRepository.findAll()).thenReturn(expected);

        final List<User> actual = studentService.findAll();

        verify(userRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByName() {
        final String userName = "Java";
        when(userRepository.findByName(userName)).thenReturn(Optional.of(user));

        final User actual = studentService.findByName(userName);

        verify(userRepository).findByName(userName);
        assertEquals(user, actual);
    }
}