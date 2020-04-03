package com.auth.AuthDemo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Test
    void redirectToHome() {

        final ModelAndView actual = homeController.redirectToHome();
        assertViewName(actual, "redirect:/home");
    }

    @Test
    void getLoginData() {
        final ModelAndView actual = homeController.getLoginData();
        assertViewName(actual, "home");
    }

    @Test
    void getContactData() {
        final ModelAndView actual = homeController.getContactData();
        assertViewName(actual, "contacts");
    }
}