package com.auth.AuthDemo.web;

import com.auth.AuthDemo.service.AuthService;
import com.auth.AuthDemo.service.QuestionService;
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
    private AuthService authService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<String> getData(Principal principal){
        principal.getName();
        return ResponseEntity.ok("This is just test " + principal.getName());
    }

    @GetMapping("/user")
    public ModelAndView getUserData(Principal principal){
        //String txt = String.format("%s", dataService.getData(principal.getName()));
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", principal.getName());
        mav.addObject("data", questionService.getAllQuestion());
        mav.setViewName("test");
        return mav;
    }

    //TODO Add other methods
}
