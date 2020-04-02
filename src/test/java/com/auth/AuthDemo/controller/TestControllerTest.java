package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoPropertiesForm;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.entity.UserTest;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestControllerTest {

    final private Long testId = 16l;
    final private String userName = "John Doe";

    @InjectMocks
    TestController testController;
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


    TestKC testKC;
    User user;

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
    void getUserData() {

        user.setUserTests(new HashSet<UserTest>(){{
            add(new UserTest() {{ setTestKC(testKC); }});
        }});

        when(testService.findById(testId)).thenReturn(testKC);
        when(principal.getName()).thenReturn(userName);
        when(userService.findByName(userName)).thenReturn(user);

        final ModelAndView actual = testController.getUserData(testId, principal);
        ModelAndViewAssert.assertViewName(actual,"redirect:/home");
    }

    @Test
    void getResults() {
        DtoPropertiesForm form = new DtoPropertiesForm();

        when(testService.findById(testId)).thenReturn(testKC);
        when(principal.getName()).thenReturn(userName);
        when(userService.findByName(userName)).thenReturn(user);

        final ModelAndView actual = testController.getResults(form, testId, principal);

        ModelAndViewAssert.assertViewName(actual,"resulttest");
        ModelAndViewAssert.assertModelAttributeAvailable(actual, "dtoTestKC");
    }
}
