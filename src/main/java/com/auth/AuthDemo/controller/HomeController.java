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

//    @GetMapping("")
//    public ResponseEntity<String> getData(Principal principal){
//        principal.getName();
//        return ResponseEntity.ok("This is just test " + principal.getName());
//    }
    @GetMapping("")
    public ModelAndView redirectToHome() {
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public ModelAndView getLoginData() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterData(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userRegistration");
        return mav;
    }

    @GetMapping("/contacts")
    public ModelAndView getContactData(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("contacts");
        return mav;
    }


}
