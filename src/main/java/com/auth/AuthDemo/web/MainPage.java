package com.auth.AuthDemo.web;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
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
    @Autowired
    ScoreCalculationService scoreCalculationService;

    @GetMapping("")
    public ResponseEntity<String> getData(Principal principal){
        principal.getName();
        return ResponseEntity.ok("This is just test " + principal.getName());
    }

    @GetMapping("result")
    public ModelAndView showResults(Principal principal){
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(2L);
        TestKC testKC = testService.findAll().get(0);
        //BigDecimal score = scoreCalculationService.updateUserScore(user, testKC);
        mav.setViewName("resultTest");
        return mav;
    }

    @GetMapping("/user")
    public ModelAndView getUserData(Principal principal){
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(2L);
        TestKC testKC = testService.findAll().get(0);
        DtoTestKC dtoTestKC = DtoConverter.toDto(user, testKC);
        System.out.println(dtoTestKC);
        mav.addObject("question", questionService.findAll());
        mav.addObject("user", user.getName());
        mav.addObject("testKC", dtoTestKC);
        mav.addObject("score", scoreCalculationService.getTestScore(dtoTestKC));
        mav.setViewName("test");
        TestKC testKC1 = DtoConverter.fromDto(user, dtoTestKC);
        System.out.println(testKC1);
        testService.update(testKC1);
        //TODO button on this test view should send whole dtoTestKC to the /result view
        return mav;
    }

    @GetMapping("/result")
    @ResponseBody
    public ModelAndView getResults(@RequestBody DtoTestKC dtoTestKC, Principal principal){
        ModelAndView mav = new ModelAndView("result");
        User user = userService.findByName(principal.getName());
        BigDecimal score = scoreCalculationService.getTestScore(dtoTestKC);
        TestKC testKC = DtoConverter.fromDto(user, dtoTestKC);
        mav.addObject("score", score);
        mav.addObject("test", dtoTestKC);
        //TODO this view needs to be created and this method should be called from localhost:8081/result
        return mav;
    }
}
