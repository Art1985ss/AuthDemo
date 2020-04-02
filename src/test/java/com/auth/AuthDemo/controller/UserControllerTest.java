package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private TestService testService;
    @Mock
    private UserService userService;
    @Mock
    private QuestionService questionService;
    @Mock
    private ScoreCalculationService scoreCalculationService;
    @Mock
    private Principal principal;

    final private Long testId = 16l;
    final private String userName = "John Doe";
    private TestKC testKC;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        testKC = new TestKC();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTestDate() {
        when(testService.findById(testId)).thenReturn(testKC);
        when(principal.getName()).thenReturn(userName);
    }

    @Test
    void newUserForm() {
    }

    @Test
    void create() {
    }

    @Test
    void testGetTestDate() {
    }

    @Test
    void testNewUserForm() {
    }

    @Test
    void testCreate() {
    }
}