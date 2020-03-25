package com.auth.AuthDemo.web;

import com.auth.AuthDemo.entity.Test;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class MainPage {
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<String> getData(Principal principal){
        principal.getName();
        return ResponseEntity.ok("This is just test " + principal.getName());
    }

    @GetMapping("/user")
    public ModelAndView getUserData(Principal principal){
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(2L);
        Test test = testService.findAll().get(0);
        test.updateUserScores();
        mav.addObject("question", questionService.findAll());
        mav.addObject("user", user.getUserTests().get(0).toString());
        mav.addObject("test", testService.findAll().get(0));
        mav.addObject("correct", questionService.findById(2L).getScore(user));
        mav.addObject("score", user.getUserTests());
        mav.setViewName("test");
        return mav;
    }

    //TODO Add other methods
}
