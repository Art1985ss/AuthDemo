package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoUser;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.entity.UserTest;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

class UserControllerTest {

    final private Long testId = 16l;
    final private String userName = "John Doe";

    @InjectMocks
    private UserController userController;
    @Mock
    private TestService testService;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;

    private TestKC testKC;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        user.setUserTests(new HashSet<UserTest>());
        testKC = new TestKC();
    }

    @Test
    void getTestDate() {
        when(principal.getName()).thenReturn(userName);
        when(testService.findAll()).thenReturn(new ArrayList<TestKC>() {{
            add(testKC);
        }});
        when(userService.findByName(userName)).thenReturn(user);

        final ModelAndView actual = userController.getTestDate(principal);

        assertViewName(actual, "user");
        assertModelAttributeAvailable(actual, "allDtoTests");
        assertModelAttributeAvailable(actual, "test");
        assertModelAttributeAvailable(actual, "user");
    }

    @Test
    void newUserForm() {
        final ModelAndView actual = userController.newUserForm();
        assertViewName(actual, "userRegistration");
        assertModelAttributeAvailable(actual, "dtoUser");
    }

    @Test
    void create() {
        final ModelAndView actual = userController.create(new DtoUser());
        assertViewName(actual, "redirect:/home");
    }
}