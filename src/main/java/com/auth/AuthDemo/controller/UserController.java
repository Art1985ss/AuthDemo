package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoUser;
import com.auth.AuthDemo.service.UserService;
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
    private UserService userService;

    @GetMapping("/user")
    public ModelAndView getTestDate (Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        DtoUser dtoUser = DtoConverter.toDto(userService.findByName(principal.getName()));
        modelAndView.addObject("user", dtoUser);
        modelAndView.addObject("test", testService.findAll());
        modelAndView.setViewName("user");
        return modelAndView;
    }

}
