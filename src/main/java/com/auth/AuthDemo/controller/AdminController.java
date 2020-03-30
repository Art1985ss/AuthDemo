package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.*;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes({ "myFancyList"})
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView getAdminData() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.findAll());
        mav.addObject("testsKC", testService.findAll());
//        System.out.println(userService.findAll().get(1).getUserTests());
        mav.setViewName("admin");
        return mav;
    }

    @GetMapping("/testmanage/test{testId}")
    public ModelAndView getAdminData(@PathVariable("testId") Long testId) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("testKC", testService.findById(testId));
        mav.setViewName("testmanage");
        return mav;
    }

    @GetMapping("/test/new")
    public ModelAndView testCreateForm() {
        ModelAndView modelAndView = new ModelAndView("createTest");
        TestKC testKC = new TestKC();
        testKC = testService.findById(1L);
//        testKC.setQuestionList(testService.findAll().get(0).getQuestionList());
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        modelAndView.addObject("dtoTest", dtoTestKC);
        return modelAndView;
    }




    @GetMapping("/test/create")
    public ModelAndView testCreate(@ModelAttribute("myFancyList") List<String> strings ) {
        ModelAndView modelAndView = new ModelAndView("experiment");
//        dtoTestKC.setQuestionList(dtoQuestionsLists.getDtoQuestions());
//        System.out.println(form.getProperties());
        System.out.println();

//        TestKC testKC = DtoConverter.fromDto(dtoTestKC);
//        testService.update(testKC);
//        modelAndView.addObject("testKC", testKC);
        return modelAndView;
    }


}
