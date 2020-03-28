package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoPropertiesForm;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    ScoreCalculationService scoreCalculationService;

    @GetMapping("/test/{testId}")
    public ModelAndView getUserData(@PathVariable("testId") Long testId, Principal principal){
        //String txt = String.format("%s", dataService.getData(principal.getName()));
        ModelAndView mav = new ModelAndView();
        User user = userService.findByName(principal.getName());
        TestKC testKC = testService.findById(testId);
        DtoTestKC dtoTestKC = DtoConverter.toDto(user, testKC);


//        System.out.println(dtoTestKC);
        mav.addObject("question", questionService.findAll());
        mav.addObject("user", user);
        mav.addObject("testKC", dtoTestKC);
        mav.addObject("score", scoreCalculationService.getTestScore(dtoTestKC));
        mav.addObject("dtoQuestion", dtoTestKC.getQuestionList().get(0));

        DtoPropertiesForm form = new DtoPropertiesForm();
        form.setProperties(dtoTestKC.getQuestionList());
        mav.addObject("form", form);
        mav.setViewName("test");
        TestKC testKC1 = DtoConverter.fromDto(user, dtoTestKC);
//        System.out.println(testKC1);
        testService.update(testKC1);
        //TODO button on this test view should send whole dtoTestKC to the /result view
        return mav;
    }

    @PostMapping("/result/test{testId}/user{userId}")
    @ResponseBody
    public ModelAndView getResults(@ModelAttribute("form") DtoPropertiesForm form,
                                   @PathVariable("testId") Long testId,
                                   @PathVariable("userId") Long userId,
                                   Principal principal){
        ModelAndView mav = new ModelAndView("resulttest");
        TestKC testKC = testService.findById(testId);
        User user = userService.findByName(principal.getName());
        DtoTestKC dtoTestKC = DtoConverter.toDto(user ,testKC);
        DtoConverter.setAnswers(dtoTestKC, form);
        scoreCalculationService.getTestScore(dtoTestKC);
        testService.update(testKC);
        mav.addObject("dtoTestKC", dtoTestKC);
//        BigDecimal score = scoreCalculationService.getTestScore(dtoTestKC);
//        TestKC testKC = DtoConverter.fromDto(user, dtoTestKC);
//        testService.update(testKC);
//        mav.addObject("score", score);
//        mav.addObject("test", dtoTestKC);
        return mav;
    }

    //    @GetMapping("/test/{testId}/question/{questionNum}")
//    public ModelAndView showQuestion(@PathVariable("testId") Long testId, @PathVariable int questionNum, Principal principal){
//        TestKC testKC = testService.findById(testId);
//        User user = userService.findByName(principal.getName());
//        DtoTestKC dtoTestKC = DtoConverter.toDto(user, testKC);
//        DtoQuestion dtoQuestion =  dtoTestKC.getQuestionList().get(questionNum);
//        ModelAndView mav = new ModelAndView("question");
//        mav.addObject("question", dtoQuestion);
//        return mav;
//    }
}
