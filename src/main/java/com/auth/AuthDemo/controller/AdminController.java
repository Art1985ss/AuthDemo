package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ModelAndView getAdminData(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("testsKC",testService.findAll());
        mav.setViewName("admin");
        return mav;
    }


}
