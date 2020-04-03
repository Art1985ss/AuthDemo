package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoPropertiesForm;
import com.auth.AuthDemo.dto.DtoQuestion;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


/**
 * Controller class related to general functionality of the webpage (login and registration)
 * and publicly accessible pages.
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    ScoreCalculationService scoreCalculationService;


    /**
     * This method maps default web page address (<IP_address>/) so that user
     * is redirected to home URL (login page).
     * @return ModelAndView bound to home.html
     */
    @GetMapping("")
    public ModelAndView redirectToHome() {
        return new ModelAndView("redirect:/home");
    }

    /**
     * Mapping for default entry point into website.
     * @return ModelAndView bound to home.html
     */
    @GetMapping("/home")
    public ModelAndView getLoginData() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }

    /**
     * Mapping for user registration form. When user clicks on Sign Up link from
     * home page he is redirected to userRegistration.html
     * @return ModelAndView bound to userRegistration.html
     */
    @GetMapping("/register")
    public ModelAndView getRegisterData(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userRegistration");
        return mav;
    }

    /**
     * Mapping for contacts page. When user in navbar clicks Contacts, this method is called.
     * @return ModelAndView bound to contacts.html
     */

    @GetMapping("/contacts")
    public ModelAndView getContactData(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("contacts");
        return mav;
    }


}
