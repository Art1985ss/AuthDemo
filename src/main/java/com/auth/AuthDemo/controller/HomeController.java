package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private TestService testService;

    @GetMapping("")
    public ResponseEntity<String> getData(Principal principal){
        principal.getName();
        return ResponseEntity.ok("This is just test " + principal.getName());
    }

    @GetMapping("/home")
    public ModelAndView getUserData(Principal principal){
        //String txt = String.format("%s", dataService.getData(principal.getName()));
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", principal.getName());
        mav.addObject("test", testService.findAll().get(0));
        mav.setViewName("test");
        return mav;
    }

    //TODO Add other methods
}
