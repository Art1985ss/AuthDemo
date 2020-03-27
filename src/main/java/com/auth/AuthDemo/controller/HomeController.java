package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoPropertiesForm;
import com.auth.AuthDemo.dto.DtoQuestion;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {
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

    @GetMapping("/test")
    public ModelAndView getUserData(Principal principal){
        //String txt = String.format("%s", dataService.getData(principal.getName()));
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(2L);
        TestKC testKC = testService.findAll().get(0);
        DtoTestKC dtoTestKC = DtoConverter.toDto(user, testKC);
//        System.out.println(dtoTestKC);
        mav.addObject("question", questionService.findAll());
        mav.addObject("user", user.getName());
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

    @PostMapping("/result")
    @ResponseBody
    public ModelAndView getResults(@ModelAttribute("form") DtoPropertiesForm form, Principal principal){
//        System.out.println("results");
        ModelAndView mav = new ModelAndView("resulttest");
        TestKC testKC = testService.findById(1L);
//        System.out.println(principal.getName());
        User user = userService.findByName(principal.getName());
        DtoTestKC dtoTestKC = DtoConverter.toDto(user ,testKC);
//        System.out.println(dtoTestKC);
        DtoConverter.setAnswers(dtoTestKC, form);
        scoreCalculationService.getTestScore(dtoTestKC);
//        System.out.println(DtoConverter.setAnswers(dtoTestKC, form));
        testService.update(testKC);
//        System.out.println(testKC);
        mav.addObject("dtoTestKC", dtoTestKC);
//        BigDecimal score = scoreCalculationService.getTestScore(dtoTestKC);
//        TestKC testKC = DtoConverter.fromDto(user, dtoTestKC);
//        testService.update(testKC);
//        mav.addObject("score", score);
//        mav.addObject("test", dtoTestKC);
        return mav;
    }


    @GetMapping("/test/{testId}/question/{questionNum}")
    public ModelAndView showQuestion(@PathVariable("testId") Long testId, @PathVariable int questionNum, Principal principal){
        TestKC testKC = testService.findById(testId);
        User user = userService.findByName(principal.getName());
        DtoTestKC dtoTestKC = DtoConverter.toDto(user, testKC);
        DtoQuestion dtoQuestion =  dtoTestKC.getQuestionList().get(questionNum);
        ModelAndView mav = new ModelAndView("question");
        mav.addObject("question", dtoQuestion);
        return mav;
    }
}
