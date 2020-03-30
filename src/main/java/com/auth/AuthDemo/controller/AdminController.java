package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView getAdminData(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.findAll());
        mav.addObject("testsKC",testService.findAll());
        System.out.println(userService.findAll().get(1).getUserTests());
        mav.setViewName("admin");
        return mav;
    }

    @GetMapping("/testmanage/test{testId}")
    public ModelAndView getAdminData(@PathVariable("testId") Long testId){
        ModelAndView mav = new ModelAndView();

        mav.addObject("testKC",testService.findById(testId));
        mav.setViewName("testmanage");
        return mav;
    }


}
