package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoPropertiesForm;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.entity.UserTest;
import com.auth.AuthDemo.entity.UserTestKey;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
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

    @GetMapping("/user/test{testId}")
    public ModelAndView getUserData(@PathVariable("testId") Long testId, Principal principal){
        ModelAndView mav = new ModelAndView();
        User user = userService.findByName(principal.getName());
        TestKC testKC = testService.findById(testId);
        UserTest userTest = new UserTest();
        userTest.setId(new UserTestKey(user.getId(), testKC.getId()));
        userTest.setUser(user);
        userTest.setTestKC(testKC);
        userTest.setScore(BigDecimal.ZERO);
        userTest.setCompleted(false);
        user.addUserTest(userTest);
//        System.out.println(user);
//        System.out.println("Updating user : ");
        userService.update(user);
//        System.out.println(user);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);

        mav.addObject("question", questionService.findAll());
        mav.addObject("user", user);
        mav.addObject("testKC", dtoTestKC);
        mav.addObject("score", scoreCalculationService.getTestScore(dtoTestKC));
        mav.addObject("dtoQuestion", dtoTestKC.getQuestionList().get(0));

        DtoPropertiesForm form = new DtoPropertiesForm();
        form.setProperties(dtoTestKC.getQuestionList());
        mav.addObject("form", form);
        mav.setViewName("test");
        //TODO button on this test view should send whole dtoTestKC to the /result view
        return mav;
    }

    @PostMapping("/user/result/test{testId}")
    @ResponseBody
    public ModelAndView getResults(@ModelAttribute("form") DtoPropertiesForm form,
                                   @PathVariable("testId") Long testId,
                                   Principal principal){
        ModelAndView mav = new ModelAndView("resulttest");
        User user = userService.findByName(principal.getName());
        TestKC testKC = testService.findById(testId);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        DtoConverter.setAnswers(dtoTestKC, form);
        scoreCalculationService.getTestScore(dtoTestKC);
        testKC = DtoConverter.fromDto(user, dtoTestKC);
        testService.update(testKC);
        mav.addObject("dtoTestKC", dtoTestKC);
        return mav;
    }
}
