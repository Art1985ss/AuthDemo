package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoQuestion;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.dto.ListCreationDto;
import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        List<User> users = userService.findAll();
        List<TestKC> testsKC = testService.findAll();

        List<BigDecimal> userScores = users.stream().map(u -> u.getScore()
                .multiply(BigDecimal.valueOf(u.getUserTests().size()), MathContext.DECIMAL128)
                .divide(BigDecimal.valueOf(testsKC.size()), new MathContext(2)))
                .collect(Collectors.toList());
        mav.addObject("users", users);
        mav.addObject("testsKC", testsKC);
        mav.addObject("userScores", userScores);
        mav.setViewName("admin");
        return mav;
    }

    @GetMapping("user/{userId}/delete")
    public ModelAndView deleteUser(@PathVariable("userId") Long id){
        userService.deleteById(id);
        return new ModelAndView("redirect:/admin/");
    }



    @GetMapping("/testmanage/test{testId}")
    public ModelAndView getAdminData(@PathVariable("testId") Long testId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("testKC", testService.findById(testId));
        mav.setViewName("testmanage");
        return mav;
    }

    @GetMapping("/test/new")
    public ModelAndView testNew() {
        ModelAndView modelAndView = new ModelAndView("createtest");
        TestKC testKC = new TestKC();
        testKC.setName("");
        testKC.setDurationMinutes(0);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        modelAndView.addObject("dtoTestKC", dtoTestKC);
        return modelAndView;
    }

    @PostMapping("/test/create")
    public ModelAndView testCreate(DtoTestKC dtoTestKC) {
        ModelAndView modelAndView = new ModelAndView("testmanage");
        dtoTestKC.setQuestionList(new ArrayList<>());
        TestKC testKC = DtoConverter.fromDto(dtoTestKC);
        testService.update(testKC);
        modelAndView.addObject("testKC", testKC);
        return modelAndView;
    }

//    @DeleteMapping("test/{testId}/delete")
    @GetMapping("test/{testId}/delete")
    public ModelAndView deleteTest(@PathVariable("testId") Long id){
        testService.deleteById(id);
        return new ModelAndView("redirect:/admin/");
    }



    @GetMapping("/test/{testID}/question/new")
    public ModelAndView questionForm(@PathVariable("testID") Long testId, @RequestParam("url") Integer ansCount) {
        ModelAndView modelAndView = new ModelAndView("question");
        TestKC testKC = testService.findById(testId);
        Question question = new Question();
        ListCreationDto lcDto = new ListCreationDto(ansCount);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        DtoQuestion dtoQuestion = DtoConverter.toDto(question);
        modelAndView.addObject("questionAnswers", lcDto);
        modelAndView.addObject("dtoTest", dtoTestKC);
        modelAndView.addObject("dtoQuestion", dtoQuestion);
        return modelAndView;
    }

    @PostMapping("/test/{testID}/question/create")
    public ModelAndView questionCreate(DtoQuestion dtoQuestion,
                                       @PathVariable("testID") Long testId,
                                       @ModelAttribute("quesitonsList") ListCreationDto listCreationDto){
//                                       @PathVariable("questionID") Long questionId){
        ModelAndView modelAndView = new ModelAndView("testmanage");
        Question question = DtoConverter.fromDto(dtoQuestion);
        question.setAnswers(listCreationDto.getAnswers());
        question.setCategory("default");
        questionService.update(question);
        dtoQuestion = DtoConverter.toDto(question);
        TestKC testKC = testService.findById(testId);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        dtoTestKC.getQuestionList().add(dtoQuestion);
        testKC = DtoConverter.fromDto(dtoTestKC);
        testService.update(testKC);
        modelAndView.addObject("testKC", dtoTestKC);
        return modelAndView;
    }

//    @DeleteMapping("test/{testId}/question/{questionId}/delete")
    @GetMapping("test/{testId}/question/{questionId}/delete")
    public ModelAndView deleteQuestion(@PathVariable("testId") Long testId,
                                       @PathVariable("questionId") Long questionId){
        questionService.deleteById(questionId);
        return new ModelAndView("redirect:/admin/testmanage/test" + testId);
    }


    @GetMapping("test/{id}/enable")
    public ModelAndView enableTest(@PathVariable("id") Long id){
        TestKC testKC = testService.findById(id);
        testKC.setEnabled(true);
        testService.update(testKC);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("test/{id}/disable")
    public ModelAndView disableTest(@PathVariable("id") Long id){
        TestKC testKC = testService.findById(id);
        testKC.setEnabled(false);
        testService.update(testKC);
        return new ModelAndView("redirect:/admin");
    }


}
