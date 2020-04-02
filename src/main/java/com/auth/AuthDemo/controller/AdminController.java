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

/**
 * Controller class used for URL mappings related to administrative tasks for tests.
 */

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

    /**
     * Method provides mapping for base administrator URL. It populates html document with
     * all user list as "users", all tests list as "testsKC" and calculated total users scores as "userScores".
     * @return ModelAndView is bound to admin.html page
     */
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

    /**
     * Mapping for user deletion. On clicking user delete button in admin.html this method is called.
     * User is deleted from database and administrator is redirected back to admin.html
     * @param id User id for deletion, passed as parameter by URL
     * @return ModelAndView bound to admin.html
     */
    @GetMapping("user/{userId}/delete")
    public ModelAndView deleteUser(@PathVariable("userId") Long id){
        userService.deleteById(id);
        return new ModelAndView("redirect:/admin/");
    }

    /**
     * Mapping for test editing form. On clicking test edit button in admin.html this method is called.
     * testmanage.html is populated with corresponding tests data as "testKC"
     * @param testId Test id for editing, passed as parameter by URL.
     * @return ModelAndView bound to testmanage.html
     */
    @GetMapping("/testmanage/test{testId}")
    public ModelAndView getAdminData(@PathVariable("testId") Long testId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("testKC", testService.findById(testId));
        mav.setViewName("testmanage");
        return mav;
    }

    /**
     * Mapping for test creating form. On clicking Add test edit button in admin.html this method is called.
     * It creates TestKC object and populates it's fields with empty values. TestKC object is converted to DTO
     * object (for data encapsulation) and passed on to createtest.html, where form input fields are
     * bound to created dtoTestKC object.
     * @return ModelAndView bound to createtest.html
     */
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

    /**
     * Mapping for POST method of input form from createtest.html. Passed DTO object is assigned a empty list of questions
     * and is converted back to TestKC object. TestKC object is saved to database and it's contents ar sent to
     * testmanage.html
     * @param dtoTestKC DTO object passed from  {@link AdminController#testNew()} method.
     * @return ModelAndView bound to testmanage.html
     */
    @PostMapping("/test/create")
    public ModelAndView testCreate(DtoTestKC dtoTestKC) {
        ModelAndView modelAndView = new ModelAndView("testmanage");
        dtoTestKC.setQuestionList(new ArrayList<>());
        TestKC testKC = DtoConverter.fromDto(dtoTestKC);
        testService.update(testKC);
        modelAndView.addObject("testKC", testKC);
        return modelAndView;
    }


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
