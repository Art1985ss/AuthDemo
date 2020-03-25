package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.service.StudentService;
import com.auth.AuthDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private TestService testService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/user")
    public ModelAndView getTestDate (Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", principal.getName());
        modelAndView.addObject("test", testService.findAll());
        modelAndView.setViewName("user");
        return modelAndView;
        //test
    }

}
