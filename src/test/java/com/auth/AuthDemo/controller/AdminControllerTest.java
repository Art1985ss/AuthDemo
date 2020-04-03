package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.entity.UserTest;
import com.auth.AuthDemo.repository.QuestionRepository;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;


class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private TestService testService;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;
    @Mock
    private QuestionService questionService;

    final private Long testId = 16l;
    final private Long questionId = 16l;

    private TestKC testKC;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        user.setUserTests(new HashSet<UserTest>());
        testKC = new TestKC();
        testKC.setId(testId);
    }

    @Test
    void getAdminData() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void testGetAdminData() {
    }

    @Test
    void testNew() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void deleteTest() {
    }

    @Test
    void questionForm() {
    }

    @Test
    void questionCreate() {
    }

    @Test
    void deleteQuestion() {
        questionService = Mockito.mock(QuestionService.class);
        final ModelAndView actual = adminController.deleteQuestion(testId , questionId);
        assertViewName(actual, "redirect:/admin/testmanage/test" + testId);
    }

    @Test
    void enableTest() {
        when(testService.findById(testId)).thenReturn(testKC);
        final ModelAndView actual = adminController.enableTest(testId);
        assertViewName(actual, "redirect:/admin");
    }

    @Test
    void disableTest() {

        when(testService.findById(testId)).thenReturn(testKC);
        final ModelAndView actual = adminController.disableTest(testId);
        assertViewName(actual, "redirect:/admin");
    }
}