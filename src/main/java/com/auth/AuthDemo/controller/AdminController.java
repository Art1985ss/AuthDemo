package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoQuestion;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.dto.ListCreationDto;
import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@SessionAttributes({ "questionAnswers"})
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;




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
        ModelAndView modelAndView = new ModelAndView("createtest");
        TestKC testKC = new TestKC();
        Long testId = testService.create(testKC);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        modelAndView.addObject("dtoTest", dtoTestKC);
        modelAndView.addObject("testId",testId);
        return modelAndView;
    }

    @PostMapping("/test/{testId}/create")
    public ModelAndView testCreate(DtoTestKC dtoTestKC,
                                   @PathVariable("testId") Long testId) {
        ModelAndView modelAndView = new ModelAndView("testmanage");
        dtoTestKC.setQuestionList(new ArrayList<>());
        TestKC testKC = DtoConverter.fromDto(dtoTestKC);
        testKC.setId(testId);
        testService.update(testKC);
        modelAndView.addObject("testKC", testKC);
        return modelAndView;
    }



    @GetMapping("/test/{testID}/question/new")
    public ModelAndView questionForm(@PathVariable("testID") Long testId, @RequestParam("url") Integer ansCount) {
        ModelAndView modelAndView = new ModelAndView("question");
        TestKC testKC = testService.findById(testId);
        Question question = new Question();
        question.setQuestion("");
        Long questionId = questionService.create(question);
        question.setId(questionId);
        ListCreationDto lcDto = new ListCreationDto(ansCount);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        DtoQuestion dtoQuestion = new DtoQuestion();
        dtoQuestion = DtoConverter.toDto(question);
        modelAndView.addObject("questionAnswers", lcDto);
        modelAndView.addObject("dtoTest", dtoTestKC);
        modelAndView.addObject("dtoQuestion", dtoQuestion);
        return modelAndView;
    }

    @PostMapping("/test/{testID}/question/{questionID}/create")
    public ModelAndView questionCreate(DtoQuestion dtoQuestion,
                                       @PathVariable("testID") Long testId,
                                       @ModelAttribute("quesitonsList") ListCreationDto listCreationDto,
                                       @PathVariable("questionID") Long questionId){
        ModelAndView modelAndView = new ModelAndView("testmanage");
        Question question = DtoConverter.fromDto(dtoQuestion);
        question.setAnswers(listCreationDto.getAnswers());
        questionService.update(question);

        TestKC testKC = testService.findById(testId);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);

        dtoQuestion.setAnswers(listCreationDto.getAnswers());
        dtoQuestion.setId(questionId);

        dtoTestKC.getQuestionList().add(dtoQuestion);
        testKC = DtoConverter.fromDto(dtoTestKC);
        testService.update(testKC);
        modelAndView.addObject("testKC", dtoTestKC);
        return modelAndView;
    }


}
