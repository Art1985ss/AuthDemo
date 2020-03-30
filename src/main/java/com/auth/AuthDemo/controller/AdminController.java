package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println(userService.findAll().get(1).getUserTests());
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
        ModelAndView modelAndView = new ModelAndView("new_test");
        TestKC testKC = new TestKC();
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        modelAndView.addObject("dtoTest", dtoTestKC);
        return modelAndView;
    }

    @GetMapping("/test/create")
    public ModelAndView testCreate(@RequestBody DtoTestKC dtoTestKC) {
        ModelAndView modelAndView = new ModelAndView("testmanage");
        TestKC testKC = DtoConverter.fromDto(dtoTestKC);
        testService.update(testKC);
        modelAndView.addObject("testKC", testKC);
        return modelAndView;
    }


}
