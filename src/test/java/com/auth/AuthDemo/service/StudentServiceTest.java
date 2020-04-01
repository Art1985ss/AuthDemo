package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.repository.UserRepository;
import com.auth.AuthDemo.service.validation.user.UserValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServiceTest {

//    @Mock
//    private UserRepository userRepository;
//
//    private User user;
//    private UserService studentService;
//    private UserValidationService userValidationService;
//    private final long expectedId = 12L;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        user = new User();
//        user.setId(expectedId);
//        studentService = new UserService(userRepository, userValidationService );
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void create() {
//        when(userRepository.save(user)).thenReturn(user);
//        assertEquals(expectedId, studentService.create(user));
//    }
//
//    @Test
//    void update() {
//        when(userRepository.save(user)).thenReturn(user);
//        assertEquals(expectedId, studentService.create(user));
//    }
//
//    @Test
//    void findById() {
//        final Optional<User> result = Optional.of(user);
//
//        when(userRepository.findById(expectedId)).thenReturn(result);
//        assertEquals(user, studentService.findById(expectedId));
//    }
//
//    @Test
//    void deleteById() {
//        studentService.deleteById(expectedId);
//        verify(userRepository).deleteById(expectedId);
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findByName() {
//    }
}